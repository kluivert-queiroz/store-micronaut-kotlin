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
import store.micronaut.kotlin.exceptions.ResourceNotFound

@Produces
@Singleton
@Requirements(
    Requires(classes = [ResourceNotFound::class, ExceptionHandler::class])
)
class ResourceNotFoundHandler(
    private val errorResponseProcessor: ErrorResponseProcessor<Any>
) : ExceptionHandler<ResourceNotFound, HttpResponse<*>> {
    override fun handle(request: HttpRequest<*>, exception: ResourceNotFound): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext
                .builder(request)
                .cause(exception).errorMessage("Could not found specified resource, try another resource.").build(),
            HttpResponse.notFound<Any>()
        )
    }
}