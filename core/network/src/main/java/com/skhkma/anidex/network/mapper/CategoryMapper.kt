package com.skhkma.anidex.network.mapper

import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.network.model.CategoryResponse

object CategoryMapper {

    fun toDomain(category: CategoryResponse): CategoryModel {
        return CategoryModel(
            id = category.id.orEmpty(),
            title = category.attributes?.title.orEmpty()
        )
    }

}