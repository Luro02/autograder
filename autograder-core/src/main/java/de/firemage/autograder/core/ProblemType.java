package de.firemage.autograder.core;

public enum ProblemType {
    UI_INPUT_SEPARATION,
    UI_OUTPUT_SEPARATION,
    AVOID_INNER_CLASSES,
    DO_NOT_USE_SYSTEM_EXIT,
    DEPRECATED_COLLECTION_USED,
    COLLECTION_IS_EMPTY_REIMPLEMENTED,
    STRING_IS_EMPTY_REIMPLEMENTED,
    INVALID_AUTHOR_TAG,
    COMMENTED_OUT_CODE,
    INCONSISTENT_COMMENT_LANGUAGE,
    INVALID_COMMENT_LANGUAGE,
    JAVADOC_STUB_DESCRIPTION,
    JAVADOC_STUB_PARAMETER_TAG,
    JAVADOC_STUB_RETURN_TAG,
    JAVADOC_STUB_THROWS_TAG,
    JAVADOC_MISSING_PARAMETER_TAG,
    JAVADOC_UNKNOWN_PARAMETER_TAG,
    JAVADOC_INCOMPLETE_RETURN_TAG,
    UNUSED_DIAMOND_OPERATOR,
    EXPLICITLY_EXTENDS_OBJECT,
    FOR_WITH_MULTIPLE_VARIABLES,
    REDUNDANT_DEFAULT_CONSTRUCTOR,
    REDUNDANT_IF_FOR_BOOLEAN,
    REDUNDANT_MODIFIER,
    REDUNDANT_VOID_RETURN,
    REDUNDANT_SELF_ASSIGNMENT,
    REDUNDANT_LOCAL_BEFORE_RETURN,
    UNUSED_IMPORT,
    PRIMITIVE_WRAPPER_INSTANTIATION,
    ASSERT,
    EXCEPTION_PRINT_STACK_TRACE,
    CUSTOM_EXCEPTION_INHERITS_RUNTIME_EXCEPTION,
    CUSTOM_EXCEPTION_INHERITS_ERROR,
    EMPTY_CATCH,
    EXCEPTION_CAUGHT_IN_SURROUNDING_BLOCK,
    RUNTIME_EXCEPTION_OR_ERROR_CAUGHT,
    OBJECTS_COMPARED_VIA_TO_STRING,
    CONSTANT_NOT_STATIC_OR_NOT_UPPER_CAMEL_CASE,
    CONSTANT_IN_INTERFACE,
    DO_NOT_HAVE_CONSTANTS_CLASS,
    STATIC_INTERFACE,
    STATIC_METHOD_IN_INTERFACE,
    DO_NOT_USE_RAW_TYPES,
    DUPLICATE_CODE,
    REASSIGNED_PARAMETER,
    DOUBLE_BRACE_INITIALIZATION,
    NON_COMPLIANT_EQUALS,
    INSTANCE_FIELD_CAN_BE_LOCAL,
    FOR_CAN_BE_FOREACH,
    OVERRIDE_ANNOTATION_MISSING,
    SYSTEM_SPECIFIC_LINE_BREAK,
    BOOLEAN_GETTER_NOT_CALLED_IS,
    MEANINGLESS_CONSTANT_NAME,
    CONFUSING_IDENTIFIER,
    SINGLE_LETTER_LOCAL_NAME,
    IDENTIFIER_IS_ABBREVIATED_TYPE,
    CONCRETE_COLLECTION_AS_FIELD_OR_RETURN_VALUE,
    LIST_NOT_COPIED_IN_GETTER,
    METHOD_USES_PLACEHOLDER_IMPLEMENTATION,
    UTILITY_CLASS_NOT_FINAL,
    UTILITY_CLASS_INVALID_CONSTRUCTOR,
    DEFAULT_PACKAGE_USED,
    EMPTY_BLOCK,
    UNUSED_CODE_ELEMENT,
    REPEATED_MATH_OPERATION,
    STATIC_FIELD_SHOULD_BE_INSTANCE,
    FIELD_SHOULD_BE_FINAL,
    STRING_COMPARE_BY_REFERENCE,
    REDUNDANT_NEGATION,
    REDUNDANT_ARRAY_INIT,
    USE_OPERATOR_ASSIGNMENT,
    JAVADOC_UNEXPECTED_TAG,
    UNMERGED_ELSE_IF,
    EXCEPTION_WITHOUT_MESSAGE,
    EMPTY_INTERFACE
}
