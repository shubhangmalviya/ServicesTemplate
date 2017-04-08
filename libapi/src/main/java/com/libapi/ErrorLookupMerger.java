package com.libapi;

import java.util.Map;

/**
 * A utility to merging the error messages based on the strategy.
 */
public class ErrorLookupMerger {

    /**
     * Merges the Error Look up messages as per the provided strategy.
     *
     * @param left the error look up mappings.
     * @param right the error look up mappings.
     * @param strategy the strategy for the merging of the parameters.
     */
    public ErrorLookupTable mergeErrorMessages(ErrorLookupTable left, ErrorLookupTable right, Strategy strategy) {
        switch (strategy) {
            case ACCEPT_LEFT:
                return mergeWithLeftPriorityStrategy(left, right);
            case ACCEPT_RIGHT:
                return mergeWithRightPriorityStrategy(left, right);
        }

        throw new RuntimeException("The chosen strategy is not yet coded.");
    }

    /**
     * Merges the Error lookup with the right parameter as priority.
     *
     * @param left the error look up mappings.
     * @param right the error look up mappings.
     */
    private ErrorLookupTable mergeWithRightPriorityStrategy(ErrorLookupTable left, ErrorLookupTable right) {

        ErrorLookupTable mergedLookupTable = new ErrorLookupTable();

        // first iterate over the right look up to add everything in it to merged set.
        for (Map.Entry<Enum, Integer> entry: right.getErrorMap().entrySet()) {
            mergedLookupTable.translate(entry.getKey(), entry.getValue());
        }

        // now iterate over the left to add whatever left has unique and leave any thing that is common to both.
        for (Map.Entry<Enum, Integer> entry: left.getErrorMap().entrySet()) {
            // check if the right does not contains the translation.
            if (!mergedLookupTable.isResolvable(entry.getKey())) {
                // if there is an extra entry in the left look up table then add it in the right look up.
                mergedLookupTable.translate(entry.getKey(), entry.getValue());
            }
        }

        return mergedLookupTable;
    }

    /**
     * Merges the Error lookup with the left parameter as priority.
     *
     * @param left the error look up mappings.
     * @param right the error look up mappings.
     */
    private ErrorLookupTable mergeWithLeftPriorityStrategy(ErrorLookupTable left, ErrorLookupTable right) {
        ErrorLookupTable mergedLookupTable = new ErrorLookupTable();

        // first iterate over the left look up to add everything in it to merged set.
        for (Map.Entry<Enum, Integer> entry: left.getErrorMap().entrySet()) {
            mergedLookupTable.translate(entry.getKey(), entry.getValue());
        }

        // now iterate over the right to add whatever left has unique and leave any thing that is common to both.
        for (Map.Entry<Enum, Integer> entry: right.getErrorMap().entrySet()) {
            // check if the right does not contains the translation.
            if (!mergedLookupTable.isResolvable(entry.getKey())) {
                // if there is an extra entry in the left look up table then add it in the right look up.
                mergedLookupTable.translate(entry.getKey(), entry.getValue());
            }
        }

        return mergedLookupTable;
    }

    public enum Strategy {
        ACCEPT_LEFT,
        ACCEPT_RIGHT
    }
}
