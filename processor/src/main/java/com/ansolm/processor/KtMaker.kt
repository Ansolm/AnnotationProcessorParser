package com.ansolm.processor

import com.ansolm.annotationprocessorparser.Class
import com.ansolm.annotationprocessorparser.poet.KotlinPoet.asField
import com.ansolm.annotationprocessorparser.poet.KotlinPoet.asFileBuilder
import com.ansolm.annotationprocessorparser.poet.KotlinPoet.asMethod
import com.ansolm.annotationprocessorparser.poet.KotlinPoet.asTypeBuilder
import com.ansolm.annotationprocessorparser.poet.KotlinPoet.stickModifier
import javax.annotation.processing.Filer

class KtMaker(val it: Class) : Maker {
    override fun make(filer: Filer) {
        System.err.println(it.fields.map { it to (it.setterOrNull to it.getterOrNull) }
            .joinToString(separator = ",", transform = { "${it.first.name} setter:${it.second.first?.name} getter:${it.second.second?.name}" })
        )
        it.asFileBuilder("", "EXE")
            .addType(
                it.asTypeBuilder("EXE")
                    .stickModifier()
                    .addProperties(it.fields.map { it.asField() })
                    .addFunctions(it.methods.map { it.asMethod() })
                    .build()
            )
            .build()
            .writeTo(filer)

    }

}