package by.it.akhmelev.JD02_04.SimpleMatlab;

public interface IVariable {
    //тут опишем основные операции
    Variable add(Variable varAdd);
    Variable sub(Variable varSub);
    Variable mul(Variable varMul);
    Variable div(Variable varDiv);

}
