package com.shopping.cart.management;

public abstract class AbstractExpressionBuilder {
    private boolean buildingComplete;

    protected final void setBuildingComplete() {
        verifyBuildingState();
        buildingComplete = true;
    }

    protected final void verifyBuildingState()
    {
        if (buildingComplete)
        {
            final String className = getClass().getName();
            final StringBuilder stringBuilder = new StringBuilder(className.length());
            stringBuilder.append("This [").append(className).append("] is done building the object and can no longer modify it.");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    protected final boolean isBuildingComplete() { return buildingComplete; }
}
