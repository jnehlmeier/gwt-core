package org.gwtproject.core.client;

import com.google.gwt.junit.client.GWTTestCase;
import org.gwtproject.core.client.Scheduler.ScheduledCommand;

public class SchedulerTest extends GWTTestCase {

    private static final int TEST_DELAY = 500000;

    @Override
    public String getModuleName() {
        return "org.gwtproject.core.CoreTest";
    }

    /**
     * Tests that a deferred command can schedule a finally command, which can schedule another
     * finally command
     */
    public void testEndToEnd() {
        final boolean[] ranEntry = {false};

        final ScheduledCommand finallyCommand = new ScheduledCommand() {
            @Override
            public void execute() {
                assertTrue(ranEntry[0]);
                Scheduler.get().scheduleFinally(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        finishTest();

                    }
                });
            }
        };

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                ranEntry[0] = true;
                Scheduler.get().scheduleFinally(finallyCommand);
            }
        });

        delayTestFinish(TEST_DELAY);
    }

//    /**
//     * Tests that an entry command can schedule a finally command where the whole
//     * thing is kicked off by a deferred command.
//     */
//    public void testEndToEndLegacy() {
//        final boolean[] ranEntry = {false};
//
//        final ScheduledCommand finallyCommand = new ScheduledCommand() {
//            @Override
//            public void execute() {
//                assertTrue(ranEntry[0]);
//                finishTest();
//            }
//        };
//
//        Scheduler.get().scheduleEntry(new ScheduledCommand() {
//            @Override
//            public void execute() {
//                ranEntry[0] = true;
//                Scheduler.get().scheduleFinally(finallyCommand);
//            }
//        });
//
//        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
//            @Override
//            public void execute() {
//                assertTrue(ranEntry[0]);
//            }
//        });
//
//        delayTestFinish(TEST_DELAY);
//    }
}