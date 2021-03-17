package views;

import interfaces.ViewModel;
import models.enums.ApplicationState;
import models.enums.Operation;
import views.uiComponents.BorderlessTextField;
import views.uiComponents.CustomButton;
import views.utils.GridBagConstraintsFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class View extends JFrame implements PropertyChangeListener {
    private final static int FONT_SIZE = 25;

    //<editor-fold desc="Panels">
    private JPanel mainContentPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonsPanel;
    //</editor-fold>

    //<editor-fold desc="Text Fields">
    private BorderlessTextField firstTextField;
    private BorderlessTextField secondTextField;
    private BorderlessTextField thirdTextField;
    //</editor-fold>

    //<editor-fold desc="Action Buttons">
    private CustomButton addBtn;
    private CustomButton subtractBtn;
    private CustomButton multiplyBtn;
    private CustomButton divideBtn;
    private CustomButton deriveBtn;
    private CustomButton integrateBtn;

    private CustomButton deleteBtn;
    private CustomButton resetBtn;
    private CustomButton equalsBtn;
    //</editor-fold>

    //<editor-fold desc="Number Buttons">
    private List<CustomButton> numBtnList;
    private CustomButton numZeroBtn;
    private CustomButton numOneBtn;
    private CustomButton numTwoBtn;
    private CustomButton numThreeBtn;
    private CustomButton numFourBtn;
    private CustomButton numFiveBtn;
    private CustomButton numSixBtn;
    private CustomButton numSevenBtn;
    private CustomButton numEightBtn;
    private CustomButton numNineBtn;
    //</editor-fold>

    //<editor-fold desc="Other Buttons">
    private CustomButton xBtn;
    private CustomButton powBtn;
    private CustomButton plusBtn;
    private CustomButton minusBtn;
    private CustomButton starBtn;
    //</editor-fold>

    private ViewModel model;

    public View(ViewModel viewModel) {
        model = viewModel;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setUpUI();

        var dim = new Dimension(400, 400);
        this.setMinimumSize(dim);
        this.setPreferredSize(dim);

        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    protected void initComponents() {
        initPanels();
        initTextFields();
        initButtons();
    }

    protected void initPanels() {
        mainContentPanel= new JPanel(new GridBagLayout());
        textFieldsPanel = new JPanel(new GridBagLayout());
        buttonsPanel    = new JPanel(new GridBagLayout());

        mainContentPanel.setBorder(null);
        textFieldsPanel .setBorder(null);
        buttonsPanel    .setBorder(null);
    }

    protected void initTextFields() {
        firstTextField  = new BorderlessTextField("");
        secondTextField = new BorderlessTextField("");
        thirdTextField  = new BorderlessTextField("");

        firstTextField  .setHorizontalAlignment(SwingConstants.RIGHT);
        secondTextField .setHorizontalAlignment(SwingConstants.RIGHT);
        thirdTextField  .setHorizontalAlignment(SwingConstants.RIGHT);

        var font = secondTextField.getFont();
        secondTextField.setFont(font.deriveFont(Font.BOLD, FONT_SIZE - (int) (0.1 * FONT_SIZE)));
        font = firstTextField.getFont();
        firstTextField.setFont(font.deriveFont(font.getStyle(), FONT_SIZE));
        font = thirdTextField.getFont();
        thirdTextField.setFont(font.deriveFont(font.getStyle(), FONT_SIZE));
    }

    protected void initButtons() {
        initNumButtons();
        initOtherButtons();
    }

    protected void initNumButtons() {
        numBtnList = new ArrayList<>();
        numBtnList.add(numZeroBtn   = new CustomButton("0"));
        numBtnList.add(numOneBtn    = new CustomButton("1"));
        numBtnList.add(numTwoBtn    = new CustomButton("2"));
        numBtnList.add(numThreeBtn  = new CustomButton("3"));
        numBtnList.add(numFourBtn   = new CustomButton("4"));
        numBtnList.add(numFiveBtn   = new CustomButton("5"));
        numBtnList.add(numSixBtn    = new CustomButton("6"));
        numBtnList.add(numSevenBtn  = new CustomButton("7"));
        numBtnList.add(numEightBtn  = new CustomButton("8"));
        numBtnList.add(numNineBtn   = new CustomButton("9"));
    }

    protected void initOtherButtons() {
        addBtn      = new CustomButton("Add");
        subtractBtn = new CustomButton("Subtract");
        multiplyBtn = new CustomButton("Multiply");
        divideBtn   = new CustomButton("Divide");
        deriveBtn   = new CustomButton("Derive");
        integrateBtn= new CustomButton("Integrate");
        deleteBtn   = new CustomButton("Delete");
        resetBtn    = new CustomButton("Reset");

        xBtn        = new CustomButton("x");
        powBtn      = new CustomButton("^");
        equalsBtn   = new CustomButton("=");
        starBtn     = new CustomButton("*");
        plusBtn     = new CustomButton("+");
        minusBtn    = new CustomButton("-");

        addBtn      .setEnabled(false);
        subtractBtn .setEnabled(false);
        multiplyBtn .setEnabled(false);
        divideBtn   .setEnabled(false);
        deriveBtn   .setEnabled(false);
        integrateBtn.setEnabled(false);
        deleteBtn   .setEnabled(false);

        powBtn      .setEnabled(false);
        equalsBtn   .setEnabled(false);
        starBtn     .setEnabled(false);
        plusBtn     .setEnabled(false);
        minusBtn    .setEnabled(true);
    }

    protected void setUpUI() {
        setUpPanels();
        setUpTextFields();
        setUpButtons();
    }

    protected void setUpPanels() {
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.add(mainContentPanel);

        mainContentPanel.add(textFieldsPanel, GridBagConstraintsFactory.getConstraints(0, 0, 1, 30));
        mainContentPanel.add(buttonsPanel   , GridBagConstraintsFactory.getConstraints(1, 0, 1, 70));
    }

    protected void setUpTextFields() {
        textFieldsPanel.add(firstTextField  , GridBagConstraintsFactory.getConstraints(0, 0));
        textFieldsPanel.add(secondTextField , GridBagConstraintsFactory.getConstraints(1, 0));
        textFieldsPanel.add(thirdTextField  , GridBagConstraintsFactory.getConstraints(2, 0));
    }

    protected void setUpButtons() {
        buttonsPanel.add(addBtn         , GridBagConstraintsFactory.getConstraints(0, 0));
        buttonsPanel.add(subtractBtn    , GridBagConstraintsFactory.getConstraints(1, 0));
        buttonsPanel.add(multiplyBtn    , GridBagConstraintsFactory.getConstraints(2, 0));
        buttonsPanel.add(divideBtn      , GridBagConstraintsFactory.getConstraints(3, 0));
        buttonsPanel.add(deriveBtn      , GridBagConstraintsFactory.getConstraints(0, 5));
        buttonsPanel.add(integrateBtn   , GridBagConstraintsFactory.getConstraints(1, 5));
        buttonsPanel.add(deleteBtn      , GridBagConstraintsFactory.getConstraints(2, 5));
        buttonsPanel.add(resetBtn       , GridBagConstraintsFactory.getConstraints(3, 5));

        buttonsPanel.add(numZeroBtn     , GridBagConstraintsFactory.getConstraints(3, 2));
        buttonsPanel.add(numOneBtn      , GridBagConstraintsFactory.getConstraints(0, 1));
        buttonsPanel.add(numTwoBtn      , GridBagConstraintsFactory.getConstraints(0, 2));
        buttonsPanel.add(numThreeBtn    , GridBagConstraintsFactory.getConstraints(0, 3));
        buttonsPanel.add(numFourBtn     , GridBagConstraintsFactory.getConstraints(1, 1));
        buttonsPanel.add(numFiveBtn     , GridBagConstraintsFactory.getConstraints(1, 2));
        buttonsPanel.add(numSixBtn      , GridBagConstraintsFactory.getConstraints(1, 3));
        buttonsPanel.add(numSevenBtn    , GridBagConstraintsFactory.getConstraints(2, 1));
        buttonsPanel.add(numEightBtn    , GridBagConstraintsFactory.getConstraints(2, 2));
        buttonsPanel.add(numNineBtn     , GridBagConstraintsFactory.getConstraints(2, 3));

        buttonsPanel.add(xBtn           , GridBagConstraintsFactory.getConstraints(3, 1));
        buttonsPanel.add(powBtn         , GridBagConstraintsFactory.getConstraints(3, 3));
        buttonsPanel.add(equalsBtn      , GridBagConstraintsFactory.getConstraints(0, 4));
        buttonsPanel.add(starBtn        , GridBagConstraintsFactory.getConstraints(1, 4));
        buttonsPanel.add(plusBtn        , GridBagConstraintsFactory.getConstraints(2, 4));
        buttonsPanel.add(minusBtn       , GridBagConstraintsFactory.getConstraints(3, 4));
    }

    protected void validateButtons() {
        disableButtons();

        if(model.getState() == ApplicationState.OPERATION_DONE) {
            return;
        }

        var target = model.getTarget();
        if(target == null) {
            return;
        }

        var op = model.getOperation();
        if(op == Operation.DERIVE || op == Operation.INTEGRATE) {
            equalsBtn.setEnabled(true);
            return;
        }

        if(target.isEmpty()) {
            xBtn.setEnabled(true);
            minusBtn.setEnabled(true);
            toggleNumButtons(true);
            return;
        }
        deleteBtn.setEnabled(true);

        Consumer<Boolean> toggleOperationButtons = (Boolean state) -> {
            if (op == Operation.NONE) {
                toggleOperationButtons(state);
                return;
            }

            equalsBtn.setEnabled(state);
        };

        var lastChar = target.charAt(target.length() - 1);

        if(lastChar == xBtn.getText().charAt(0)) {
            powBtn.setEnabled(true);
            plusBtn.setEnabled(true);
            minusBtn.setEnabled(true);
            toggleOperationButtons.accept(true);
            return;
        }

        if(lastChar == powBtn.getText().charAt(0)) {
            toggleNumButtons(true);
            return;
        }

        if(lastChar == starBtn.getText().charAt(0)) {
            xBtn.setEnabled(true);
            return;
        }

        if(lastChar == plusBtn.getText().charAt(0) || lastChar == minusBtn.getText().charAt(0)) {
            xBtn.setEnabled(true);
            toggleNumButtons(true);
            return;
        }

        if(target.matches(".*\\^\\d+$")) {
            plusBtn.setEnabled(true);
            minusBtn.setEnabled(true);
            toggleNumButtons(true);
            toggleOperationButtons.accept(true);
            return;
        }

        if(target.matches(".*[+-]?\\d+$")) {
            xBtn.setEnabled(true);
            starBtn.setEnabled(true);
            plusBtn.setEnabled(true);
            minusBtn.setEnabled(true);
            toggleNumButtons(true);
            toggleOperationButtons.accept(true);
            return;
        }
    }

    protected void disableButtons() {
        xBtn.setEnabled(false);
        powBtn.setEnabled(false);
        starBtn.setEnabled(false);
        plusBtn.setEnabled(false);
        minusBtn.setEnabled(false);
        toggleNumButtons(false);
        toggleOperationButtons(false);
        deleteBtn.setEnabled(false);
        equalsBtn.setEnabled(false);
    }

    protected void toggleOperationButtons(boolean state) {
        addBtn      .setEnabled(state);
        subtractBtn .setEnabled(state);
        multiplyBtn .setEnabled(state);
        divideBtn   .setEnabled(state);
        deriveBtn   .setEnabled(state);
        integrateBtn.setEnabled(state);
    }

    protected void toggleNumButtons(boolean state) {
        numBtnList.forEach(btn -> {
            btn.setEnabled(state);
        });
    }

    // Add controller events
    public void addTextBtnActionListener(ActionListener al) {
        numBtnList.forEach(btn -> {
            btn.addActionListener(al);
        });

        xBtn    .addActionListener(al);
        powBtn  .addActionListener(al);
        starBtn .addActionListener(al);
        plusBtn .addActionListener(al);
        minusBtn.addActionListener(al);
    }

    public void addAddBtnActionListener(ActionListener al) {
        addBtn.addActionListener(al);
    }

    public void addSubtractBtnActionListener(ActionListener al) {
        subtractBtn.addActionListener(al);
    }

    public void addMultiplyBtnActionListener(ActionListener al) {
        multiplyBtn.addActionListener(al);
    }

    public void addDivideBtnActionListener(ActionListener al) {
        divideBtn.addActionListener(al);
    }

    public void addDeriveBtnActionListener(ActionListener al) {
        deriveBtn.addActionListener(al);
    }

    public void addIntegrateBtnActionListener(ActionListener al) {
        integrateBtn.addActionListener(al);
    }

    public void addDeleteBtnActionListener(ActionListener al) {
        deleteBtn.addActionListener(al);
    }

    public void addResetBtnActionListener(ActionListener al) {
        resetBtn.addActionListener(al);
    }

    public void addEqualsBtnActionListener(ActionListener al) {
        equalsBtn.addActionListener(al);
    }

    // Listen to model events
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        firstTextField  .setText(model.getFirstPolynomialText());
        secondTextField .setText(model.getOperationText());
        thirdTextField  .setText(model.getSecondPolynomialText());
        validateButtons();
    }
}
