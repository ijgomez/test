import org.example.test.Formula

class MyFormulaImpl implements Formula {

    @Override
    int execute(Object[] params) {
        return params[0] + params[1]
    }
}