# Statuses
status-compiling = Compiling
status-spotbugs = Running SpotBugs
status-pmd = Running PMD
status-cpd = Running Copy/Paste-Detection
status-model = Building the code model
status-docker = Building the Docker image
status-tests = Executing tests
status-integrated = Running integrated analysis

# Linters
linter-cpd = Copy/Paste-Detection
linter-spotbugs = SpotBugs
linter-pmd = PMD
linter-integrated = Integrated Analysis

# CPD
duplicate-code = Duplizierter Code ({$lines}): {$first-path}:{$first-start}-{$first-end} und {$second-path}:{$second-start}-{$second-end}

# API
is-empty-reimplemented-desc = Verwende isEmpty() statt size() == 0 oder ähnlichem Code
is-empty-reimplemented-exp = Verwende isEmpty()

old-collection-desc = Javas alte Collection-Typen sollten nicht verwendet werden (Vector -> ArrayList, Stack -> Deque, Hashtable -> HashMap)
old-collection-exp-vector = Verwende ArrayList statt Vector
old-collection-exp-hashtable = Verwende HashMap statt Hashtable
old-collection-exp-stack = Verwende Dequeue statt Stack

string-is-empty-desc = Verwende String#isEmpty statt '.equals("")' oder '.length() == 0' (bzw. die Negation)
string-is-empty-exp-emptiness = Benutze 'isEmpty()' statt '{$exp}' um auf Leerheit zu prüfen
string-is-empty-exp-non-emptiness = Benutze '!<...>isEmpty()' statt '{$exp}' um auf mindestens ein Element zu prüfen

# Comment
author-tag-invalid-desc = Der @author-Tag ist nicht valide
author-tag-invalid-exp = Der @author-tag ist nicht valide

commented-out-code-desc = Unbenutzter Code sollte entfernt werden
commented-out-code-exp = Dieser auskommentierte Code sollte entfernt werden

comment-language-desc = Alle Kommentare (einschließlich Javadoc and inline comments) müssen entweder auf Deutsch oder auf Englisch sein
comment-language-exp-invalid = Dieser Kommentar ist weder auf Deutsch noch auf Englisch, sonder scheint {$lang} zu sein
comment-language-exp-english = Der Code enthält deutsche und englische Kommentare. Dieser Kommentar ist auf Englisch. Ein deutscher Kommentar befindet sich bei {$path}:{$line}
comment-language-exp-german = Der Code enthält deutsche und englische Kommentare. Dieser Kommentar ist auf Deutsch. Ein englischer Kommentar befindet sich bei {$path}:{$line}

javadoc-param-desc = Javadoc-Kommentare müssen alle deklarierten Parameter beschreiben
javadoc-param-exp-missing = Der Parameter '{$param}' wird im Javadoc-Kommentar nicht erwähnt
javadoc-param-exp-unknown = Der Javadoc-Kommentar erwähnt den Parameter '{$param}', dieser wird allerdings nicht deklariert

javadoc-return-null-desc = Methoden müssen in dem @return-Tag angeben, wenn sie null zurückgeben können
javadoc-return-null-exp = Die Methode {$method} kann null zurückgeben, der @return-Tag erwähnt das aber nicht

javadoc-stub-desc = Automatisch generierte Javadoc-Kommentare müssen für die konkrete Methode angepasst werden
javadoc-stub-exp-desc = Die Beschreibung des Javadoc-Kommentars ist leer
javadoc-stub-exp-param = Nichtssagende Beschreibung für den Parameter '{$param}'
javadoc-stub-exp-return = Nichtssagende Beschreibung für den Rückgabewert
javadoc-stub-exp-throws = Nichtssagende Beschreibung für die Exception {$exp}

# Complexity
diamond-desc = Verwende den 'diamond operator' statt den generischen Typ zu wiederholen: new Foo<>()
diamond-exp = Verwende den 'diamond operator'

extends-object-desc = Explizit von Object zu erben ist unnötig
extends-object-exp = Unnötiges 'extends Object'

for-loop-var-desc = for-Schleifen sollten genau eine lokal deklarierte Kontrollvariable haben
for-loop-var-exp = for-Schleifen sollten genau eine lokal deklarierte Kontrollvariable haben

implicit-constructor-desc = Der Standard-Konstruktor wird durch den Compiler automatisch generiert wenn kein anderer Konstruktor vorhanden ist
implicit-constructor-exp = Unnötiger Standard-Konstruktor

redundant-if-for-bool-desc = Boolesche Werte können direkt zugewiesen bzw. zurückgegeben werden und müssen nicht in ifs geschachtelt werden
redundant-if-for-bool-exp-return = {$exp} kann direkt zurückgegeben werden
redundant-if-for-bool-exp-assign = '{$exp}' kann direkt zu '{$target}' zugewiesen werden

redundant-modifier-desc = Einige Modifizierer sind implizit
redundant-modifier-exp = Unnötiger Modifizierer

redundant-return-desc = 'return' am Ende einer Void-Methode ist implizit
redundant-return-exp = Unnötiges return

self-assignment-desc = Eine Variable sich selbst zuzuweisen ist unnötig
self-assignment-exp = Nutzlose Zuweisung von '{$rhs}' zu '{$lhs}'

redundant-local-return-desc = Unnötige Deklaration einer lokalen Variable, die sofort zurückgegeben wird
redundant-local-return-exp = Der Wert kann direkt zurückgegeben werden

unused-import-desc = Unbenutzter Import
unused-import-exp = Unbenutzter Import

wrapper-instantiation-desc = Wrapper-Klassen sollten nicht direkt instanziiert werden
wrapper-instantiation-exp = Wrapper-Klassen sollten nicht direkt instanziiert werden

repeated-math-operation = Einfache mathematische Operationen sollten nicht wiederholt werden, sondern durch die entsprechende Operation "ein Level höher" ersetzt werden (z.B. n + n + n => 3 * n; n * n * n => Math.pow(n, 3)).
repeated-math-operation-mul = Hier sollte Math.pow verwendet werden, anstatt '{$var}' {$count}-mal mit sich selbst zu multiplizieren.
repeated-math-operation-plus = Hier sollte eine Multiplikation mit {$count} verwendet werden, anstatt '{$var}' {$count}-mal mit sich selbst zu addieren.

# Debug
assert-used-desc = Assertions eignen sich nicht zur Fehlerbehandlung
assert-used-exp = Assertion benutzt

print-stack-trace-desc = Stack Traces sollten in der Abgabe nicht ausgegeben werden
print-stack-trace-exp = Stack Traces sollten in der Abgabe nicht ausgegeben werden

# Exceptions
custom-exception-inheritance-desc = Selbstdefinierte Exceptions sollten nicht von RuntimeException oder Error erben
custom-exception-inheritance-exp-runtime = Selbstdefinierte Exceptions sollten immer Checked Exceptions sein
custom-exception-inheritance-exp-error = Selbstdefinierte Exceptions sollten nicht von Error erben

empty-catch-desc = Alle Exceptions sollten angemessen behandelt werden
empty-catch-exp = Leerer catch-Block

exception-controlflow-desc = Exceptions sollten innerhalb von Methoden nicht für Kontrollfluss benutzt werden (z.B. durch Werfen und Fangen in derselben Methode)
exception-controlfow-exp-caught = {$exp} wird geworfen und im umgebenden Block sofort wieder gefangen

runtime-ex-caught-desc = RuntimeExceptions sollten niemals gefangen werden (abgesehen von NumberFormatException)
runtime-ex-caught-exp = RuntimeException vom Typ {$exp} gefangen

# General
compare-objects-desc = Objekte sollten mit equals verglichen werden, anstatt sie zum Vergleich in Strings umzuwandeln
compare-objects-exp = Implementiere eine equals-Methode für den Typ {$type} und verwende sie zum Vergleichen

constant-naming-qualifier-desc = Konstanten sollten 'static final' sein und einen UPPER_SNAKE_CASE-Namen haben
constant-naming-qualifier-exp = Die Konstante '{$field}' der Klasse {$class} sollte statisch sein und einen UPPER_SNAKE_CASE-Namen haben

constants-interfaces-desc = Geteilte Konstanten sollten in Enums oder Utility-Klassen und nicht in Interfaces gespeichert werden
constants-interfaces-exp = Interfaces sollten keine Attribute haben

param-reassign-desc = Parameter sollten nicht neu zugewiesen werden
param-reassign-exp = Parameter sollten nicht neu zugewiesen werden

double-brace-desc = Die obskure 'Double Brace'-Syntax sollte vermieden werden
double-brace-exp = ie obskure 'Double Brace'-Syntax sollte vermieden werden

equals-handle-null-argument-desc = Die equals-Methode sollte null-Werte behandeln
equals-handle-null-argument-exp = Die equals-Methode sollte null-Werte behandeln

field-local-desc = Attribute sollten in lokale Variablen umgewandelt werden falls sie vor jedem Lesen überschrieben werden
field-local-exp = Das Attribut '{$field}' der Klasse {$class} sollte eine lokale Variable sein, da sie in jeder Methode vor dem ersten Lesen überschrieben wird

for-foreach-desc = for-Schleife sollte eine for-each-Schleife sein
for-foreach-exp = for-Schleife sollte eine for-each-Schleife sein

missing-override-desc = Fehlendes @Override
missing-override-exp = Fehlendes @Override

system-dependent-linebreak-desc = Es sollten immer systemunabhängige Zeilenumbrüche wie der Wert von System.lineSeparator() oder '%n' in format-Strings verwendet werden
system-dependent-linebreak-exp = Systemabhängiger Zeilenumbruch (\n) benutzt

# Naming
bool-getter-name-desc = Getter für boolesche Werte sollten das Präfix 'is' haben
bool-getter-name-exp = Die Methode sollte isY() statt getY() heißen

constants-name-desc = Konstanten sollte aussagekräftige Namen haben - z.B. AUTHOR_INDEX statt FIRST_INDEX
constants-name-exp-string = Der Name '{$name}' ist nicht aussagekräftig gegeben den Wert '{$value}'
constants-name-exp-number = The name '{$name}' ist nicht aussagekräftig gegeben den Wert {$value}

linguistic-desc = Das Code-Element hat einen verwirrenden Namen. Siehe https://pmd.github.io/latest/pmd_rules_java_codestyle.html#linguisticnaming
linguistic-exp = Das Code-Element hat einen verwirrenden Namen. Siehe https://pmd.github.io/latest/pmd_rules_java_codestyle.html#linguisticnaming

variable-names-desc = Lokale Variablen sollten aussagekräftige Werte haben
variable-name-exp-single-letter = Der Bezeichner ist nicht aussagekräftig
variable-name-exp-type = Unnötige Abkürzung

# OOP
concrete-collection-desc = Statt konkreten Collections sollten immer allgemeine Interfaces verwendet werden (z.B. List stat ArrayList)
concrete-collection-exp = Statt konkreten Collections sollten immer allgemeine Interfaces verwendet werden (z.B. List stat ArrayList)

list-getter-desc = Veränderbare Collections müssen in Gettern kopiert werden
list-getter-exp = Die Collection is veränderbar, wird aber nicht kopiert

method-abstract-desc = Leere Methoden in abstrakten Klassen sollten abstrakt sein
method-abstract-exp = {$type}::{$method} sollte abstrakt sein, anstatt eine Platzhalter-Implementierung anzugeben

utility-desc = Utility-Klassen müssen final sein und genau einen parameterlosen Konstruktor haben
utility-exp-final = Utility-Klasse ist nicht final
utility-exp-constructor = Utility-Klassen müssen genau einen parameterlosen Konstruktor haben
utility-exp-field = Utility-Klassen dürfen ausschließlich finale Attribute haben

# Structure

default-package-desc = Das default-Paket sollte nicht verwendet werden
default-package-exp = Das default-Paket sollte nicht verwendet werden

# Unnecessary

empty-block-desc = Leerer Block (if / else / for / while / switch / try)
empty-block-exp-if = Leerer if/else-Block
empty-block-exp-while = Leerer while-Block
empty-block-exp-try = Leerer try-Block
empty-block-exp-finally = Leerer finally-Block
empty-block-exp-switch = Leerer switch-Block

unused-element-desc = Unbenutztes Code-Element (lokale Variable / Parameter / privates Attribut / private method)
unused-element-exp = Unbenutzt