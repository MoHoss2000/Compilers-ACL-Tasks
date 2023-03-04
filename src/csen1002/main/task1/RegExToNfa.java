package csen1002.main.task1;

/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

import java.util.*;


public class RegExToNfa {
    private final String regex;
    private final char[] alphabet;


    /**
     * Constructs an NFA corresponding to a regular expression based on Thompson's
     * construction
     *
     * @param input The alphabet and the regular expression in postfix notation for
     *              which the NFA is to be constructed
     */
    public RegExToNfa(String input) {
        String[] splitted = input.split("#");

        String alphabetString = splitted[0];

        String[] alphabetArrayString = alphabetString.split(";");

        alphabet = new char[alphabetArrayString.length];
        for (int i = 0; i < alphabetArrayString.length; i++) {
            alphabet[i] = alphabetArrayString[i].charAt(0);
        }

        regex = splitted[1];
    }

    private NFA thompsonConstruction(String regex) {
        Stack<NFA> stack = new Stack<>();
        int n = 0; // last state number

        for (int i = 0; i < regex.length(); i++) {
            char c = regex.charAt(i);
            switch (c) {
                case '|': // Union
                    NFA op2 = stack.pop();
                    NFA op1 = stack.pop();

                    int start1 = op1.startState;
                    int start2 = op2.startState;

                    int newStartState = n++;
                    int newAcceptState = n++;

                    TreeSet<Integer> newStates = op1.states;
                    newStates.addAll(op2.states);
                    newStates.add(newStartState);
                    newStates.add(newAcceptState);


                    TreeSet<StateTransition> newTransitions = op1.transitions;
                    newTransitions.addAll(op2.transitions);

                    newTransitions.add(new StateTransition(newStartState, start1, 'e'));
                    newTransitions.add(new StateTransition(newStartState, start2, 'e'));
                    newTransitions.add(new StateTransition(op1.getAcceptState(), newAcceptState, 'e'));
                    newTransitions.add(new StateTransition(op2.getAcceptState(), newAcceptState, 'e'));

                    TreeSet<Integer> newAcceptStates = new TreeSet<Integer>();
                    newAcceptStates.add(newAcceptState);

                    NFA newNFA = new NFA(newStates, alphabet, newTransitions, newStartState, newAcceptStates);
                    stack.push(newNFA);
                    break;
                case '.': // Concatenation
                    op2 = stack.pop();
                    op1 = stack.pop();

                    int finalState1 = op1.getAcceptState();
                    int startState2 = op2.startState;

                    newStates = op1.states;
                    op2.states.remove(startState2);
                    newStates.addAll(op2.states);

                    for (StateTransition stateTransition : op2.transitions) {
                        if (stateTransition.sourceState == startState2) {
                            stateTransition.sourceState = finalState1;
                        }
                    }

                    newTransitions = op1.transitions;
                    newTransitions.addAll(op2.transitions);

                    newAcceptStates = new TreeSet<Integer>(op2.acceptStates);

                    newNFA = new NFA(newStates, alphabet, newTransitions, op1.startState, newAcceptStates);
                    stack.push(newNFA);
                    break;
                case '*': // Kleene star
                    op1 = stack.pop();

                    start1 = op1.startState;
                    int accept1 = op1.getAcceptState();

                    newStartState = n++;
                    newAcceptState = n++;

                    newStates = op1.states;
                    newStates.add(newStartState);
                    newStates.add(newAcceptState);

                    newTransitions = op1.transitions;
                    newTransitions.add(new StateTransition(newStartState, start1, 'e'));
                    newTransitions.add(new StateTransition(newStartState, newAcceptState, 'e'));
                    newTransitions.add(new StateTransition(accept1, start1, 'e'));
                    newTransitions.add(new StateTransition(accept1, newAcceptState, 'e'));

                    newAcceptStates = new TreeSet<>();
                    newAcceptStates.add(newAcceptState);

                    newNFA = new NFA(newStates, op1.alphabet, newTransitions, newStartState, newAcceptStates);
                    stack.push(newNFA);
                    break;
                default: // Single symbol
                    stack.push(new NFA(c, n));
                    n += 2;
                    break;
            }
        }
        // The final NFA is the only one remaining on the stack
        return stack.pop();
    }

    /**
     * @return Returns a formatted string representation of the NFA. The string
     * representation follows the one in the task description
     */
    @Override
    public String toString() {
        NFA nfa = thompsonConstruction(regex);

        return nfa.toString();

    }

    private static class StateTransition implements Comparable<StateTransition> {
        private final int destinationState;
        private final char transitionSymbol;
        private int sourceState;

        public StateTransition(int sourceState, int destinationState, char transitionSymbol) {
            this.sourceState = sourceState;
            this.destinationState = destinationState;
            this.transitionSymbol = transitionSymbol;
        }

        public String toString() {
            return sourceState + "," + transitionSymbol + "," + destinationState;
        }

        @Override
        public int compareTo(StateTransition o) {
            if (this.sourceState != o.sourceState) {
                return Integer.compare(this.sourceState, o.sourceState);
            } else if (this.transitionSymbol != o.transitionSymbol) {
                return Character.compare(this.transitionSymbol, o.transitionSymbol);
            } else {
                return Integer.compare(this.destinationState, o.destinationState);
            }
        }
    }

    private static class NFA {
        TreeSet<Integer> states;
        char[] alphabet;
        TreeSet<StateTransition> transitions;
        int startState;
        TreeSet<Integer> acceptStates;


        public NFA(TreeSet<Integer> states, char[] alphabet, TreeSet<StateTransition> transitions, int initialState, TreeSet<Integer> acceptStates) {
            this.states = states;
            this.alphabet = alphabet;
            this.transitions = transitions;
            this.startState = initialState;
            this.acceptStates = acceptStates;
        }

        public NFA(char input, int n) {
            states = new TreeSet<Integer>(Arrays.asList(n, n + 1));
            alphabet = new char[]{input};
            transitions = new TreeSet<StateTransition>();
            transitions.add(new StateTransition(n, n + 1, input));

            startState = n;
            acceptStates = new TreeSet<>(List.of(n + 1));
        }

        public int getAcceptState() {
            return acceptStates.iterator().next();
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();

            // Append set of states
            for (int state : states) {
                sb.append(state).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append('#');

            // Append alphabet
            for (char symbol : alphabet) {
                sb.append(symbol).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append('#');

            // Append transitions
            for (StateTransition transition : transitions) {
                sb.append(transition.toString()).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append('#');

            // Append start state
            sb.append(startState).append('#');

            // Append accept states
            for (int state : acceptStates) {
                sb.append(state).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);


            return sb.toString();
        }
    }


}
