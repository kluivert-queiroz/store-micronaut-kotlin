package store.micronaut.kotlin.datavalidation

import io.micronaut.core.annotation.Introspected
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Introspected
data class CreateProductDTO(
    @Schema(example = "Notebook")
    @field:NotBlank var title: String?,
    @Schema(example = "A very good notebook for programming costs more than R\$300.")
    @field:NotBlank var description: String?,
    @Schema(example = "4000")
    @field:Min(1)
    var price: Double?
)