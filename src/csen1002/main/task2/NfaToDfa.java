package csen1002.main.task2;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

public class NfaToDfa {

    public static class DeadState extends DfaCombinedState {
        public DeadState() {
            super(new TreeSet<Integer>(List.of(-1)));
        }
    }

	public static class DfaCombinedState implements Comparable<DfaCombinedState> {
		private TreeSet<Integer> states;

        public DfaCombinedState(int state) {
            this(new TreeSet<Integer>(List.of(state)));
        }

		public DfaCombinedState(TreeSet<Integer> states) {
			this.states = states;
		}

		public TreeSet<Integer> getStates() {
			return states;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int state : states) {
				sb.append(state).append('/');
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}

		@Override
		public int compareTo(DfaCombinedState o) {
            if (this.states.size() == 0) {
                return -1;
            } else if (o.states.size() == 0) {
                return 1;
            } else {
                int thisFirst = this.states.first();
                int oFirst = o.states.first();
                if (thisFirst != oFirst) {
                    return Integer.compare(thisFirst, oFirst);
                } else {
                    TreeSet<Integer> thisTail = new TreeSet<Integer>(this.states);
                    thisTail.remove(thisFirst);

                    TreeSet<Integer> oTail = new TreeSet<Integer>(o.states);
                    oTail.remove(oFirst);

                    return new DfaCombinedState(thisTail).compareTo(new DfaCombinedState(oTail));
                }
            }
		}
	}


	private static class DfaStateTransition implements Comparable<DfaStateTransition> {
		private final DfaCombinedState destinationState;
		private final char transitionSymbol;
		private DfaCombinedState sourceState;

		public DfaStateTransition(DfaCombinedState sourceState, DfaCombinedState destinationState, char transitionSymbol) {
			this.sourceState = sourceState;
			this.destinationState = destinationState;
			this.transitionSymbol = transitionSymbol;
		}

		public String toString() {
			return sourceState + "," + transitionSymbol + "," + destinationState;
		}

		@Override
		public int compareTo(DfaStateTransition o) {
			if (this.sourceState != o.sourceState) {
				return this.sourceState.compareTo(o.sourceState);
			} else if (this.transitionSymbol != o.transitionSymbol) {
				return Character.compare(this.transitionSymbol, o.transitionSymbol);
			} else {
				return this.destinationState.compareTo(o.destinationState);
			}
		}
	}

	private static class NfaStateTransition implements Comparable<NfaStateTransition> {
		private final int destinationState;
		private final char transitionSymbol;
		private int sourceState;

		public NfaStateTransition(int sourceState, int destinationState, char transitionSymbol) {
			this.sourceState = sourceState;
			this.destinationState = destinationState;
			this.transitionSymbol = transitionSymbol;
		}

		public NfaStateTransition(String string) {
			String[] parts = string.split(",");
			sourceState = Integer.parseInt(parts[0]);
			transitionSymbol = parts[1].charAt(0);
			destinationState = Integer.parseInt(parts[2]);
		}


		public String toString() {
			return sourceState + "," + transitionSymbol + "," + destinationState;
		}

		@Override
		public int compareTo(NfaStateTransition o) {
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
		TreeSet<NfaStateTransition> transitions;
		int startState;
		TreeSet<Integer> acceptStates;


		public NFA(TreeSet<Integer> states, char[] alphabet, TreeSet<NfaStateTransition> transitions, int initialState, TreeSet<Integer> acceptStates) {
			this.states = states;
			this.alphabet = alphabet;
			this.transitions = transitions;
			this.startState = initialState;
			this.acceptStates = acceptStates;
		}

		public NFA(char input, int n) {
			states = new TreeSet<Integer>(Arrays.asList(n, n + 1));
			alphabet = new char[]{input};
			transitions = new TreeSet<NfaStateTransition>();
			transitions.add(new NfaStateTransition(n, n + 1, input));

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
			for (NfaStateTransition transition : transitions) {
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


	private final NFA nfa;
	/**
	 * Constructs a DFA corresponding to an NFA
	 *
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	public NfaToDfa(String input) {
		String[] splitted = input.split("#");
		TreeSet<Integer> states = new TreeSet<>();

		for (String state : splitted[0].split(";")) {
			states.add(Integer.parseInt(state));
		}

		splitted[1] = splitted[1].replace(";", "");
		char[] alphabet = splitted[1].toCharArray();

		TreeSet<NfaStateTransition> transitions = new TreeSet<>();

		for (String transitionString : splitted[2].split(";")) {
			transitions.add(new NfaStateTransition(transitionString));
		}

		int startState = Integer.parseInt(splitted[3]);

		TreeSet<Integer> acceptStates = new TreeSet<>();

		for (String acceptState : splitted[4].split(";")) {
			acceptStates.add(Integer.parseInt(acceptState));
		}

		nfa = new NFA(states, alphabet, transitions, startState, acceptStates);
        System.out.println(nfa);
	}

	public Hashtable<Integer, DfaCombinedState> getEpsilonClosureTable() {
		Hashtable<Integer, DfaCombinedState> epsilonClosure = new Hashtable<>();

		for (int state : nfa.states) {
			epsilonClosure.put(state, getEpsilonClosure(state));
		}

		return epsilonClosure;
	}

	public DfaCombinedState getEpsilonClosure(int state) {
		TreeSet<Integer> epsilonClosure = new TreeSet<>();
		epsilonClosure.add(state);

		Stack<Integer> stack = new Stack<>();
		stack.push(state);

		while (!stack.isEmpty()) {
			int currentState = stack.pop();

			for (NfaStateTransition transition : nfa.transitions) {
				if (transition.sourceState == currentState && transition.transitionSymbol == 'e') {
					if (!epsilonClosure.contains(transition.destinationState)) {
						epsilonClosure.add(transition.destinationState);
						stack.push(transition.destinationState);
					}
				}
			}
		}

		return new DfaCombinedState(epsilonClosure);
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		String nfaString = "0;1;2;3#a;b#0,a,0;0,b,0;0,b,1;1,a,2;1,e,2;2,b,3;3,a,3;3,b,3#0#3";
		String nfaString2 = "0;1;2;3;4;5;6;7;8;9;10;11;12#c;d;i;n#0,i,1;1,e,8;1,e,10;2,c,3;3,e,9;4,n,5;5,e,4;5,e,7;6,e,4;6,e,7;7,e,9;8,e,2;8,e,6;9,e,12;10,d,11;11,e,12#0#12";
//		assertEquals("-1;0;1/2/4/6/7/8/9/10/12;3/9/12;4/5/7/9/12;11/12#c;d;i;n#-1,c,-1;-1,d,-1;-1,i,-1;-1,n,-1;0,c,-1;0,d,-1;0,i,1/2/4/6/7/8/9/10/12;0,n,-1;1/2/4/6/7/8/9/10/12,c,3/9/12;1/2/4/6/7/8/9/10/12,d,11/12;1/2/4/6/7/8/9/10/12,i,-1;1/2/4/6/7/8/9/10/12,n,4/5/7/9/12;3/9/12,c,-1;3/9/12,d,-1;3/9/12,i,-1;3/9/12,n,-1;4/5/7/9/12,c,-1;4/5/7/9/12,d,-1;4/5/7/9/12,i,-1;4/5/7/9/12,n,4/5/7/9/12;11/12,c,-1;11/12,d,-1;11/12,i,-1;11/12,n,-1#0#1/2/4/6/7/8/9/10/12;3/9/12;4/5/7/9/12;11/12", nfaToDfa.toString());
		NfaToDfa nfaToDfa = new NfaToDfa(nfaString2);
		System.out.println(nfaToDfa.getEpsilonClosureTable());
	}

}
