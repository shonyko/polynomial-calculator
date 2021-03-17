package models.viewModels;

import interfaces.ViewModel;
import models.enums.ApplicationState;
import models.enums.Operation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppStateModel implements ViewModel {
    private final PropertyChangeSupport support;

    private int state;
    private int operation;

    private String firstPolynomialText;
    private String operationText;
    private String secondPolynomialText;

    public AppStateModel() {
        support = new PropertyChangeSupport(this);
        reset();
    }

    public void reset() {
        resetState();
        resetTexts();
        operation = Operation.NONE;
    }

    @Override
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void resetState() {
        state = ApplicationState.INSERT_FIRST_POLYNOMIAL;
    }

    @Override
    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    @Override
    public String getTarget() {
        String target = null;

        if(state == ApplicationState.INSERT_FIRST_POLYNOMIAL) {
            target = firstPolynomialText;
        }
        if(state == ApplicationState.INSERT_SECOND_POLYNOMIAL) {
            target = secondPolynomialText;
        }

        return target;
    }

    public void setTarget(String text) {
        if(state == ApplicationState.INSERT_FIRST_POLYNOMIAL) {
            setFirstPolynomialText(text);
        }
        if(state == ApplicationState.INSERT_SECOND_POLYNOMIAL) {
            setSecondPolynomialText(text);
        }
    }

    public void resetTexts() {
        firstPolynomialText = "";
        operationText       = "";
        secondPolynomialText= "";
    }

    public String getFirstPolynomialText() {
        return firstPolynomialText;
    }

    public void setFirstPolynomialText(String firstPolynomialText) {
        this.firstPolynomialText = firstPolynomialText;
    }

    public String getOperationText() {
        return operationText;
    }

    public void setOperationText(String operationText) {
        this.operationText = operationText;
    }

    public String getSecondPolynomialText() {
        return secondPolynomialText;
    }

    public void setSecondPolynomialText(String secondPolynomialText) {
        this.secondPolynomialText = secondPolynomialText;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void sendUpdate() {
        support.firePropertyChange("all", false, true);
    }
}
