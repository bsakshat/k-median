import gurobi.*;

public class GurobiModel {

    public void solveLP(final int[][] c, int k){
        solveModel(c, k, false);
    }

    public void solveIP(final int[][] c, int k){
        solveModel(c, k, true);
    }

    private void solveModel(final int[][] c, int k, boolean IP) {
        int n = c[0].length;

        try {
            GRBEnv env  = new GRBEnv(true);
            env.set("logFile", "kmedian.log");
            env.start();

            GRBModel model = new GRBModel(env);

            model.set(GRB.DoubleParam.MIPGap, 0);

            GRBVar[][] x = new GRBVar[n][];

            for (int i = 0; i < n; i++){
                x[i] = new GRBVar[n];
                for (int j = 0; j < n; j++){
                    x[i][j] = model.addVar(0.0, 1.0, c[i][j], GRB.CONTINUOUS, i + ", " + j);
                }
            }

            model.update();

            if (IP) {
                for (int j = 0; j < n; j++)
                    x[j][j].set(GRB.CharAttr.VType, GRB.BINARY);
            }

            GRBLinExpr expr1 = new GRBLinExpr();
            for (int j = 0; j < n; j++) {
                expr1.addTerm(1, x[j][j]);
            }
            model.addConstr(expr1, GRB.EQUAL, k, "c0");

            for (int i = 0; i < n; i++) {
                GRBLinExpr expr2 = new GRBLinExpr();
                for (int j = 0; j < n; j++){
                    expr2.addTerm(1, x[i][j]);
                }

                model.addConstr(expr2, GRB.EQUAL, 1, "c1");
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        model.addConstr(x[i][j], GRB.LESS_EQUAL, x[j][j], "c2");
                    }
                }
            }

            model.optimize();

            double lowerBound = model.get(GRB.DoubleAttr.ObjBound);
            System.out.println("Lower Bound Objective Function Value:" + lowerBound);

            if (IP){
                System.out.println("Vertices to be picked:");
                for (int i = 0; i < n; i++)
                    if (x[i][i].get(GRB.DoubleAttr.X) > 0.5)
                        System.out.print(i + " ");
                System.out.println();
            }

        }
        catch (GRBException e){
            System.out.println("Error Code " + e.getErrorCode());
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }
}
