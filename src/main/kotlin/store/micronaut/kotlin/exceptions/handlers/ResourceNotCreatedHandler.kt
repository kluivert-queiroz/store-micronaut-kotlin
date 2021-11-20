package store.micronaut.kotlin.exceptions.handlers

import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import jakarta.inject.Singleton
import store.micronaut.kotlin.exceptions.ResourceNotCreated

@Produces
@Singleton
@Requirements(
    Requires(classes = [ResourceNotCreated::class, ExceptionHandler::class])
)
class ResourceNotCreatedHandler(
    private val errorResponseProcessor: ErrorResponseProcessor<Any>
) : ExceptionHandler<ResourceNotCreated, HttpResponse<*>> {
    override fun handle(request: HttpRequest<*>, exception: ResourceNotCreated): HttpResponse<*> {
        println("Resource Not Created")
        return errorResponseProcessor.processResponse(
            ErrorContext
                .builder(request)
                .cause(exception).errorMessage("Could not create resource.").build(),
            HttpResponse.serverError<Any>()
        )
    }
}