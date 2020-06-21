package com.alpha.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义注解
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListVaLue,Integer> {
    private Set<Integer> set=new HashSet<>();
    //判断是否校验成功
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return set.contains(value);
    }


    @Override
    public void initialize(ListVaLue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val :vals){
            set.add(val);
        }

    }
}
