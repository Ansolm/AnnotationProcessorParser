package com.ansolm.processor

import com.ansolm.annotationprocessorparser.Class
import com.ansolm.annotationprocessorparser.poet.JavaPoet.asField
import com.ansolm.annotationprocessorparser.poet.JavaPoet.asMethod
import com.ansolm.annotationprocessorparser.poet.JavaPoet.asTypeBuilder
import com.ansolm.annotationprocessorparser.poet.JavaPoet.stickModifier
import com.squareup.javapoet.JavaFile
import javax.annotation.processing.Filer

class JavaMaker(val it: Class) : Maker {
    override fun make(filer: Filer) {
        JavaFile.builder(
            it.packet.name,
            it.asTypeBuilder("EXT")
                .stickModifier()
                .addFields(it.fields.map { it.asField() })
                .addMethods(it.methods.map { it.asMethod() })
                .build()
        )
            .build().writeTo(filer)
    }
}