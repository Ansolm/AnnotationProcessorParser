package com.ansolm.example

import com.ansolm.annotations.FieldAnnotation
import com.ansolm.annotations.ParserAnnotation
import java.lang.Deprecated


@ParserAnnotation
@Deprecated
class SuperKotlin {
    @FieldAnnotation
    var name: Int = 132

}