package de.firemage.codelinter.core.spoon.check;

import spoon.reflect.CtModel;
import spoon.reflect.factory.Factory;

public interface Check {
    void check(CtModel model, Factory factory);

}