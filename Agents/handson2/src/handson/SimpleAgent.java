package handson;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.util.HashMap;
import java.util.Map;

public class SimpleAgent extends Agent {

    regresionObject regresion;
    Map<Integer, Object> params;
    windowBuilder windowDatas;
    windowBuilder windowResults;
    windowBuilder dialog = new windowBuilder();
    tableFormat tb;
    int result = 0;
    datas dt = new datas();

    @Override
    protected void setup() {
        //THIS IS MY AGENT
        System.out.println("Agent Regresion started.");
        result = dialog.confirmPanel();
        addBehaviour(new FourStepBehaviour());
    }

    private class FourStepBehaviour extends Behaviour {

        private int step = 1;

        @Override
        public void action() {
            switch (step) {
                case 1 -> {
                    System.out.println(result);
                    System.out.println("--- First Behaviour ---");
                    regresion = new regresionObject(dt.getYearsExperience(), dt.getSalary());
                    System.out.println("Years experience -Salary");
                    if (result == 0) {
                        //PARAMS TO TABLE 1
                        params = new HashMap<>();
                        params.put(0, dt.getSalary());
                        params.put(1, dt.getYearsExperience());
                        tb = new tableFormat(params);
                        //WINDOW BUILD
                        windowDatas = new windowBuilder("Salary - Years Experience");
                        windowDatas.tableView(
                                windowDatas.tableComponent(new String[]{"Index", "Salary", "yearsExperience"}, tb.getDataTable())
                        );
                        //PARAMS TO TABLE 2
                        params = new HashMap<>();
                        params.put(0,
                                new double[]{1.2, 10.6, 11}
                        );
                        params.put(1,
                                regresion.multiPrediction(
                                        new double[]{1.2, 10.6, 11}
                                )
                        );
                        tb = new tableFormat(params);
                        //WINDOW BUILD
                        windowResults = new windowBuilder("Salary - Years Experience Results");
                        windowResults.resultView(
                                windowResults.labelComponen(
                                        "Determination: " + regresion.getDetermination()
                                ),
                                windowResults.labelComponen(
                                        "Correlation: " + regresion.getCorrelation()
                                ), windowResults.tableComponent(
                                new String[]{"Index", "Point", "Result"},
                                tb.getDataTable()
                        ));
                    } else {
                        System.out.println("Determination: " + regresion.getDetermination());
                        System.out.println("Correlation: " + regresion.getCorrelation());
                        regresion.multiPredictionConsole(new double[]{1.2, 10.6, 11});
                    }
                }

                case 2 -> {
                    regresion = new regresionObject(dt.getAge(), dt.getPremium());
                    System.out.println("--- Second Behaviour ---");
                    System.out.println("Age - Premium insurance");
                    if (result == 0) {
                        //PARAMS TABLE
                        params = new HashMap<>();
                        params.put(0, dt.getAge());
                        params.put(1, dt.getPremium());
                        tableFormat tb = new tableFormat(params);
                        //WINDOW BUILD
                        windowDatas = new windowBuilder("Age - Premium insurance");
                        windowDatas.tableView(
                                windowDatas.tableComponent(new String[]{"Index", "Age", "Premium"}, tb.getDataTable())
                        );
                        //PARAMS TO TABLE 2
                        params = new HashMap<>();
                        params.put(0,
                                new double[]{18, 33, 34}
                        );
                        params.put(1,
                                regresion.multiPrediction(
                                        new double[]{18, 33, 34}
                                )
                        );
                        tb = new tableFormat(params);
                        //WINDOW BUILD
                        windowResults = new windowBuilder("Age - Premium insurance");
                        windowResults.resultView(
                                windowResults.labelComponen(
                                        "Determination: " + regresion.getDetermination()
                                ),
                                windowResults.labelComponen(
                                        "Correlation: " + regresion.getCorrelation()
                                ), windowResults.tableComponent(
                                new String[]{"Index", "Point", "Result"},
                                tb.getDataTable()
                        ));
                    } else {
                        System.out.println("Determination: " + regresion.getDetermination());
                        System.out.println("Correlation: " + regresion.getCorrelation());
                        regresion.multiPredictionConsole(new double[]{18, 33, 34});
                    }

                }
                case 3 -> {
                    regresion = new regresionObject(dt.getYear(), dt.getMediaAge());
                    System.out.println("--- Third Behaviour ---");
                    System.out.println("Years - Media Age India population");
                    if (result == 0) {
                        //TABLE PARAMS
                        params = new HashMap<>();
                        params.put(0, dt.getYear());
                        params.put(1, dt.getMediaAge());
                        tableFormat tb = new tableFormat(params);
                        //WINDOW BUILD
                        windowDatas = new windowBuilder("Years - Media Age India population");
                        windowDatas.tableView(
                                windowDatas.tableComponent(new String[]{"Index", "Years", "Media Age"}, tb.getDataTable())
                        );
                        //PARAMS TO TABLE 2
                        params = new HashMap<>();
                        params.put(0,
                                new double[]{2050, 1955, 2051}
                        );
                        params.put(1,
                                regresion.multiPrediction(
                                        new double[]{2050, 1955, 2051}
                                )
                        );

                        tb = new tableFormat(params);
                        //WINDOW BUILD
                        windowResults = new windowBuilder("Years - Media Age India population");
                        windowResults.resultView(
                                windowResults.labelComponen(
                                        "Determination: " + regresion.getDetermination()
                                ),
                                windowResults.labelComponen(
                                        "Correlation: " + regresion.getCorrelation()
                                ), windowResults.tableComponent(
                                new String[]{"Index", "Point", "Result"},
                                tb.getDataTable()
                        ));
                    } else {
                        System.out.println("Determination: " + regresion.getDetermination());
                        System.out.println("Correlation: " + regresion.getCorrelation());
                        regresion.multiPredictionConsole(new double[]{2050, 1955, 2051});
                    }
                }
            }
            step++;
        }

        @Override
        public boolean done() {
            return step == 4;
        }

        @Override
        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}
