package com.jminango.controller

import com.jminango.converters.NumberConverter
import com.jminango.exceptions.UnsupportedMathOperationException
import com.jminango.math.SimpleMath
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MathController {


    private val math = SimpleMath()

    @RequestMapping(value = ["sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne : String?,
            @PathVariable(value = "numberTwo") numberTwo : String?
    ) : Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) return throw UnsupportedMathOperationException("Please set a numeric value")
        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
    }

    @RequestMapping(value = ["subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(@PathVariable(value = "numberOne") numberOne : String?,
                    @PathVariable(value = "numberTwo") numberTwo : String?
    ) : Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) return throw UnsupportedMathOperationException("Please set a numeric value")
        return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
    }


    @RequestMapping(value = ["multiplication/{numberOne}/{numberTwo}"])
    fun multiplication(@PathVariable(value = "numberOne") numberOne : String?,
                       @PathVariable(value = "numberTwo") numberTwo : String?
    ) : Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) return throw UnsupportedMathOperationException("Please set a numeric value")
        return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
    }

    @RequestMapping(value = ["division/{numberOne}/{numberTwo}"])
    fun division(@PathVariable(value = "numberOne") numberOne : String?,
                 @PathVariable(value = "numberTwo") numberTwo : String?
    ) : Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) return throw UnsupportedMathOperationException("Please set a numeric value")
        return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
    }

    @RequestMapping(value = ["mean/{numberOne}/{numberTwo}"])
    fun mean(@PathVariable(value = "numberOne") numberOne : String?,
             @PathVariable(value = "numberTwo") numberTwo : String?
    ) : Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) return throw UnsupportedMathOperationException("Please set a numeric value")
        return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
    }
    @RequestMapping(value = ["squareRoot/{number}"])
    fun squareRoot(@PathVariable(value = "number") number : String?) : Double {
        if (!NumberConverter.isNumeric(number)) return throw UnsupportedMathOperationException("Please set a numeric value")
        return math.squareRoot(NumberConverter.convertToDouble(number))
    }



}