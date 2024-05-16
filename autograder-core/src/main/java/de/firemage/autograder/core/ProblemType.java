package de.firemage.autograder.core;

public enum ProblemType {
    /**
     * If the code is split into multiple packages, all input must happen in one package. Otherwise, one class must do all input.
     * <br/>
     * Has false positives.
     */
    @HasFalsePositives
    UI_INPUT_SEPARATION,

    /**
     * If the code is split into multiple packages, all output must happen in one package. Otherwise, one class must do all output.
     * <br/>
     * Has false positives.
     */
    @HasFalsePositives
    UI_OUTPUT_SEPARATION,

    /**
     * Checks for non-private inner types (static classes, interfaces, enums, local types)
     * <br>
     * This check ignores inner classes that are not static, because there are cases where
     * that can be useful.
     */
    AVOID_INNER_CLASSES,

    /**
     * Advises to use String.formatted instead of String.format for simple format strings
     */
    @HasFalsePositives
    USE_STRING_FORMATTED,

    /**
     * Reports all java.util.Optional<Boolean> types
     */
    OPTIONAL_TRI_STATE,

    /**
     * Reports all java.util.Optional types that are used as function parameters
     */
    OPTIONAL_ARGUMENT,

    /**
     * Reports all uses of labels
     */
    AVOID_LABELS,

    /**
     * Advises to use Arrays.fill(array, val) instead of Arrays.fill(array, val, 0, array.length)
     */
    SIMPLIFY_ARRAYS_FILL,

    /**
     * Reports unused assignments
     */
    REDUNDANT_ASSIGNMENT,

    /**
     * Reports local variables & fields that shadow files in parent classes, skipping constructors and simple setters
     */
    AVOID_SHADOWING,

    COLLECTIONS_N_COPIES,
    DO_NOT_USE_SYSTEM_EXIT,
    SCANNER_MUST_BE_CLOSED,
    EQUALS_HASHCODE_COMPARABLE_CONTRACT,
    UNCHECKED_TYPE_CAST,
    TOO_MANY_EXCEPTIONS,
    IMPLEMENT_COMPARABLE,
    MAGIC_STRING,
    CONSTANT_NAME_CONTAINS_VALUE,
    DEPRECATED_COLLECTION_USED,
    COLLECTION_IS_EMPTY_REIMPLEMENTED,
    STRING_IS_EMPTY_REIMPLEMENTED,
    INVALID_AUTHOR_TAG,
    COMMENTED_OUT_CODE,
    INCONSISTENT_COMMENT_LANGUAGE,
    MUTABLE_ENUM,
    CHAR_RANGE,
    INVALID_COMMENT_LANGUAGE,
    JAVADOC_STUB_DESCRIPTION,
    JAVADOC_STUB_PARAMETER_TAG,
    JAVADOC_STUB_RETURN_TAG,
    JAVADOC_STUB_THROWS_TAG,
    JAVADOC_MISSING_PARAMETER_TAG,
    JAVADOC_UNKNOWN_PARAMETER_TAG,
    PACKAGE_NAMING_CONVENTION,
    JAVADOC_UNDOCUMENTED_THROWS,
    UNUSED_DIAMOND_OPERATOR,
    EXPLICITLY_EXTENDS_OBJECT,
    FOR_WITH_MULTIPLE_VARIABLES,
    BINARY_OPERATOR_ON_BOOLEAN,
    TODO_COMMENT,
    REDUNDANT_DEFAULT_CONSTRUCTOR,
    REDUNDANT_IF_FOR_BOOLEAN,
    REDUNDANT_MODIFIER,
    REDUNDANT_MODIFIER_VISIBILITY_ENUM_CONSTRUCTOR,
    REDUNDANT_VOID_RETURN,
    REDUNDANT_SELF_ASSIGNMENT,
    REDUNDANT_VARIABLE,
    REDUNDANT_BOOLEAN_EQUAL,
    REDUNDANT_ELSE,
    COLLECTION_ADD_ALL,
    AVOID_RECOMPILING_REGEX,
    UNUSED_IMPORT,
    PRIMITIVE_WRAPPER_INSTANTIATION,
    ASSERT,
    EXCEPTION_PRINT_STACK_TRACE,
    CUSTOM_EXCEPTION_INHERITS_RUNTIME_EXCEPTION,
    CUSTOM_EXCEPTION_INHERITS_ERROR,
    EMPTY_CATCH,
    EXCEPTION_CAUGHT_IN_SURROUNDING_BLOCK,
    EXCEPTION_SHOULD_NEVER_BE_CAUGHT,
    RUNTIME_EXCEPTION_CAUGHT,
    OBJECTS_COMPARED_VIA_TO_STRING,
    FIELD_SHOULD_BE_CONSTANT,
    CONSTANT_IN_INTERFACE,
    DO_NOT_HAVE_CONSTANTS_CLASS,
    STATIC_METHOD_IN_INTERFACE,
    DO_NOT_USE_RAW_TYPES,
    DUPLICATE_CODE,
    TOO_FEW_PACKAGES,
    TRY_CATCH_COMPLEXITY,
    AVOID_STATIC_BLOCKS,
    METHOD_SHOULD_BE_STATIC,
    REASSIGNED_PARAMETER,
    DOUBLE_BRACE_INITIALIZATION,
    FOR_CAN_BE_FOREACH,
    LOOP_SHOULD_BE_DO_WHILE,
    LOOP_SHOULD_BE_FOR,
    OVERRIDE_ANNOTATION_MISSING,
    SYSTEM_SPECIFIC_LINE_BREAK,
    BOOLEAN_GETTER_NOT_CALLED_IS,
    MEANINGLESS_CONSTANT_NAME,
    CONFUSING_IDENTIFIER,
    SINGLE_LETTER_LOCAL_NAME,
    IDENTIFIER_IS_ABBREVIATED_TYPE,
    IDENTIFIER_CONTAINS_TYPE_NAME,
    USE_GUARD_CLAUSES,
    CONCRETE_COLLECTION_AS_FIELD_OR_RETURN_VALUE,
    LEAKED_COLLECTION_RETURN,
    LEAKED_COLLECTION_ASSIGN,
    METHOD_USES_PLACEHOLDER_IMPLEMENTATION,
    UTILITY_CLASS_NOT_FINAL,
    UTILITY_CLASS_INVALID_CONSTRUCTOR,
    UTILITY_CLASS_ABSTRACT,
    DEFAULT_PACKAGE_USED,
    COMMON_REIMPLEMENTATION_ARRAY_COPY,
    COMMON_REIMPLEMENTATION_STRING_REPEAT,
    COMMON_REIMPLEMENTATION_MAX_MIN,
    COMMON_REIMPLEMENTATION_SQRT,
    COMMON_REIMPLEMENTATION_HYPOT,
    COMMON_REIMPLEMENTATION_ADD_ALL,
    COMMON_REIMPLEMENTATION_ADD_ENUM_VALUES,
    COMMON_REIMPLEMENTATION_ARRAYS_FILL,
    USE_MODULO_OPERATOR,
    COMMON_REIMPLEMENTATION_SUBLIST,
    COMMON_REIMPLEMENTATION_ITERABLE_DUPLICATES,
    ABSTRACT_CLASS_WITHOUT_ABSTRACT_METHOD,
    SHOULD_BE_INTERFACE,
    COMPOSITION_OVER_INHERITANCE,
    USE_ENTRY_SET,
    EMPTY_BLOCK,
    UNUSED_CODE_ELEMENT,
    UNUSED_CODE_ELEMENT_PRIVATE,
    SIMILAR_IDENTIFIER,
    REPEATED_MATH_OPERATION,
    STATIC_FIELD_SHOULD_BE_INSTANCE,
    FIELD_SHOULD_BE_FINAL,
    STRING_COMPARE_BY_REFERENCE,
    REDUNDANT_NEGATION,
    USE_OPERATOR_ASSIGNMENT,
    JAVADOC_UNEXPECTED_TAG,
    UNMERGED_ELSE_IF,
    MERGE_NESTED_IF,
    EXCEPTION_WITHOUT_MESSAGE,
    EMPTY_INTERFACE,
    SUPPRESS_WARNINGS_USED,
    COMPLEX_REGEX,
    USE_FORMAT_STRING,
    LOCAL_VARIABLE_SHOULD_BE_CONSTANT,
    USE_ENUM_COLLECTION,
    INSTANCEOF,
    INSTANCEOF_EMULATION,
    COMPARE_CHAR_VALUE,
    REDUNDANT_CATCH,
    SHOULD_BE_ENUM_ATTRIBUTE,
    CLOSED_SET_OF_VALUES,
    TYPE_HAS_DESCRIPTIVE_NAME,
    IDENTIFIER_REDUNDANT_NUMBER_SUFFIX,
    IMPORT_TYPES,
    USE_DIFFERENT_VISIBILITY,
    USE_DIFFERENT_VISIBILITY_PEDANTIC,
    USE_DIFFERENT_VISIBILITY_PUBLIC_FIELD,
    MULTI_THREADING,
    COMPARE_TO_ZERO,
    EQUALS_USING_HASHCODE,
    EQUALS_UNSAFE_CAST,
    EQUALS_INCOMPATIBLE_TYPE,
    INCONSISTENT_HASH_CODE,
    UNDEFINED_EQUALS,
    EQUALS_BROKEN_FOR_NULL,
    NON_OVERRIDING_EQUALS,
    ARRAYS_HASHCODE,
    EQUALS_REFERENCE,
    ARRAY_AS_KEY_OF_SET_OR_MAP,
    MULTIPLE_INLINE_STATEMENTS,
    UNNECESSARY_BOXING,
    AVOID_STRING_CONCAT,
    UNNECESSARY_COMMENT,
    OBJECT_DATATYPE,
    NUMBER_FORMAT_EXCEPTION_IGNORED,
    REDUNDANT_UNINITIALIZED_VARIABLE
}
