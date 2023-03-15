package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.lens.*
import ru.ac.uniyar.domain.operations.ListEquipmentOperation
import ru.ac.uniyar.domain.storage.EMPTY_UUID
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.web.models.NewEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class AddEquipmentHandler(
    private val htmlView: ContextAwareViewRender,
    private val listEquipmentOperation: ListEquipmentOperation,
):HttpHandler {
    val nameFormLens = FormField.string().required("name")
    val productIdFormLens = FormField.string().required("productId")
    val descriptionFormLens = FormField.string().required("description")
    val submissionDateFormLens = FormField.localDate().required("submissionDate")
    val EquipmentFormLens = Body.webForm(
        Validator.Feedback,
        nameFormLens,
        productIdFormLens,
        descriptionFormLens,
        submissionDateFormLens
    ).toLens()
    override fun invoke(request: Request): Response {
        val webForm = EquipmentFormLens(request)
        if(webForm.errors.isEmpty()) {
            val device= Equipment(
                    EMPTY_UUID,
                    nameFormLens(webForm),
                    productIdFormLens(webForm),
                    descriptionFormLens(webForm),
                    submissionDateFormLens(webForm)
                )
            listEquipmentOperation.add(device)

            return  Response(Status.FOUND).header("Location", "/equipment")
        } else {
            return  Response(Status.OK).with(htmlView(request) of NewEquipmentVM(webForm))
        }
    }
}