package com.riptide.ddplatform.domin.dto;

import javax.validation.groups.Default;

public class ValidatorGroups {
    public interface Add extends Default {}
    public interface Update extends Default{}
    public interface Delete extends Default{}
    public interface SelectItem extends Default{}

}
