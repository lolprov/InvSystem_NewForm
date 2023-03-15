package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import ru.ac.uniyar.web.models.NewEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowFormEquipmentHandler(
    private val htmlView: ContextAwareViewRender,
):HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(Status.OK).with(htmlView(request) of NewEquipmentVM() )

    }
}