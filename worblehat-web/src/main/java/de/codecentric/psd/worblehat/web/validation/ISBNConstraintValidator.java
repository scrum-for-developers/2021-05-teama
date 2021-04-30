package de.codecentric.psd.worblehat.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.ISBNValidator;

public class ISBNConstraintValidator implements ConstraintValidator<ISBN, String> {

  @Override
  public boolean isValid(String isbn, ConstraintValidatorContext context) {
    // Don't validate null, empty and blank strings, since these are validated by @NotNull,
    // @NotEmpty and @NotBlank
    if (StringUtils.isNotBlank(isbn)) {
      ISBNValidator validator = ISBNValidator.getInstance();
      return validator.isValidISBN10(isbn) || validator.isValidISBN13(isbn);
    }
    return true;
  }
}
