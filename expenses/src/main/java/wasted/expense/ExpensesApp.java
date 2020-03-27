package wasted.expense;

import wasted.jdbc.QueryExecutor;

public class ExpensesApp {

    public static void main(String... args) {
        QueryExecutor executor = new QueryExecutor(args[0]);
    }
}