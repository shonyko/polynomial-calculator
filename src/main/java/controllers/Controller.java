package controllers;

import exceptions.PolynomialDivisionByZeroException;
import models.Polynomial;
import models.enums.ApplicationState;
import models.enums.Operation;
import views.uiComponents.CustomButton;
import models.utils.PolynomialCalculator;
import models.viewModels.AppStateModel;
import views.View;

import java.awt.event.ActionEvent;
import java.util.function.BiConsumer;

public class Controller {
    private final AppStateModel model;
    private final View view;

    public Controller(AppStateModel model, View view) {
        this.model = model;
        this.view = view;

        model.addPropertyChangeListener(view);

        setUpUserEvents();
    }

    private void setUpUserEvents() {
        setTextBtnActionListeners();
        setActionBtnActionListeners();
        setEqualsBtnActionListener();
    }

    private void setTextBtnActionListeners() {
        view.addTextBtnActionListener(e -> {
            var target = model.getTarget();
            CustomButton btn = (CustomButton) e.getSource();
            model.setTarget(target + btn.getText());

            model.sendUpdate();
        });

        view.addDeleteBtnActionListener(e -> {
            var target = model.getTarget();
            model.setTarget(target.replaceFirst(".$", ""));

            model.sendUpdate();
        });

        view.addResetBtnActionListener(e -> {
            model.reset();

            model.sendUpdate();
        });
    }

    private void setActionBtnActionListeners() {
        BiConsumer<ActionEvent, Integer> f = (ActionEvent e, Integer op) -> {
            model.setState(ApplicationState.INSERT_SECOND_POLYNOMIAL);
            model.setOperation(op);

            CustomButton btn = (CustomButton) e.getSource();
            model.setOperationText(btn.getText());

            model.sendUpdate();
        };

        view.addAddBtnActionListener        (e -> f.accept(e, Operation.ADD));
        view.addSubtractBtnActionListener   (e -> f.accept(e, Operation.SUBTRACT));
        view.addMultiplyBtnActionListener   (e -> f.accept(e, Operation.MULTIPLY));
        view.addDivideBtnActionListener     (e -> f.accept(e, Operation.DIVIDE));
        view.addDeriveBtnActionListener     (e -> f.accept(e, Operation.DERIVE));
        view.addIntegrateBtnActionListener  (e -> f.accept(e, Operation.INTEGRATE));
    }

    private void setEqualsBtnActionListener() {
        view.addEqualsBtnActionListener(e -> {
            model.setState(ApplicationState.OPERATION_DONE);

            try {
                var firstPolynomial = Polynomial.fromString(model.getFirstPolynomialText());
                var secondPolynomial= Polynomial.fromString(model.getSecondPolynomialText());
                updateOperationResult(firstPolynomial, secondPolynomial);

            } catch (Exception ex) {
                model.resetTexts();
                model.setOperationText("ERR: " + ex.getMessage());
            }

            model.sendUpdate();
        });
    }

    private void updateOperationResult(Polynomial firstPolynomial, Polynomial secondPolynomial) throws PolynomialDivisionByZeroException {
        String result = "";
        model.resetTexts();
        switch (model.getOperation()) {
            case Operation.ADD      -> result = PolynomialCalculator.add(firstPolynomial, secondPolynomial).toString();
            case Operation.SUBTRACT -> result = PolynomialCalculator.subtract(firstPolynomial, secondPolynomial).toString();
            case Operation.MULTIPLY -> result = PolynomialCalculator.multiply(firstPolynomial, secondPolynomial).toString();
            case Operation.DERIVE   -> result = PolynomialCalculator.derive(firstPolynomial).toString();
            case Operation.INTEGRATE-> result = PolynomialCalculator.integrate(firstPolynomial).toString();
            case Operation.DIVIDE   -> {
                result = PolynomialCalculator.getDivisionResult(firstPolynomial, secondPolynomial).toString();
                var remainder = PolynomialCalculator.getDivisionRemainder(firstPolynomial, secondPolynomial).toString();
                model.setOperationText("Remainder: " + remainder);
            }
        }
        model.setFirstPolynomialText(result);
    }
}
