package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.lens.FormField
import org.http4k.lens.WebForm
import org.http4k.lens.localDate
import org.http4k.lens.string
import org.http4k.routing.path
import ru.ac.uniyar.domain.operations.FetchEquipmentOperation
import ru.ac.uniyar.domain.operations.ListEquipmentOperation
import ru.ac.uniyar.web.models.NewEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.util.*

class ShowUpdateFormHandler(
    private val htmlView: ContextAwareViewRender,
    private val fetchEquipmentOperation: FetchEquipmentOperation,
):HttpHandler {
    override fun invoke(request: Request): Response {
        val indexString = request.path("id").orEmpty()
        val index = UUID.fromString(indexString) ?: return Response(Status.BAD_REQUEST)
        val user= fetchEquipmentOperation.fetch(index)?: return Response(Status.BAD_REQUEST)
        val nameFormLens = FormField.string().required("name")
        val productIdFormLens = FormField.string().required("productId")
        val descriptionFormLens = FormField.string().required("description")
        val submissionDateFormLens = FormField.localDate().required("submissionDate")
        val webForm = WebForm().with(nameFormLens of user.name,
            productIdFormLens of user.productId,
            descriptionFormLens of user.description,
             submissionDateFormLens of user.submissionDate)
        return  Response(Status.OK).with(htmlView(request) of NewEquipmentVM(webForm))

    }
}