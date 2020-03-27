package wasted.user;

import wasted.jdbc.QueryExecutor;

public class UserRepo {

    private final QueryExecutor queryExecutor;

    public UserRepo(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }
}