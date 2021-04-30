package de.codecentric.psd.worblehat.web.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ISBNConstraintValidatorTest {

  private ISBNConstraintValidator isbnConstraintValidator;

  private ConstraintValidatorContext constraintValidatorContext;

  @BeforeEach
  public void setUp() {
    isbnConstraintValidator = new ISBNConstraintValidator();
    constraintValidatorContext = mock(ConstraintValidatorContext.class);
  }

  @Test
  void initializeShouldTakeIsbn() {
    ISBN isbn = mock(ISBN.class);
    assertDoesNotThrow(() -> isbnConstraintValidator.initialize(isbn));
  }

  @Test
  void shouldReturnTrueIfBlank() {
    boolean actual = isbnConstraintValidator.isValid("", constraintValidatorContext);
    assertTrue(actual);
  }

  @Test
  void shouldReturnTrueIfValid10DigitISBN() {
    boolean actual = isbnConstraintValidator.isValid("0132350882", constraintValidatorContext);
    assertTrue(actual);
  }

  @Test
  void shouldReturnFalseIfInvalid10DigitISBN() {
    boolean actual = isbnConstraintValidator.isValid("0123459789", constraintValidatorContext);
    assertFalse(actual);
  }

  @Test
  void shouldReturnTrueIfValid13DigitISBN() {
    boolean actual = isbnConstraintValidator.isValid("9783551354013", constraintValidatorContext);
    assertTrue(actual);
  }

  @Test
  void shouldReturnTrueIfValid13DigitISBNWithSeparators() {
    boolean actual = isbnConstraintValidator.isValid("978-3-86680-192-9", constraintValidatorContext);
    assertTrue(actual);
  }

  @Test
  void shouldReturnFalseIfInvalid13DigitISBNWithSeparator() {
    boolean actual = isbnConstraintValidator.isValid("978-3551354013", constraintValidatorContext);
    assertFalse(actual);
  }

  @Test
  void shouldReturnFalseIfInvalid13DigitISBN() {
    boolean actual = isbnConstraintValidator.isValid("0123459789123", constraintValidatorContext);
    assertFalse(actual);
  }
}
