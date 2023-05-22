/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

grammar Task9;

@members {
	/**
	 * Compares two integer numbers
	 *
	 * @param x the first number to compare
	 * @param y the second number to compare
	 * @return 1 if x is equal to y, and 0 otherwise
	 */
	public static int equals(int x, int y) {
	    return x == y ? 1 : 0;
	}
}

s returns [int check]:
    a c [$a.y, $a.x, 1, 0] b {$check = $c.slf * $c.suf * equals($a.n, $b.n);};

a returns [int n, int x, int y]:
    'a' a1=a {
            $n = $a1.n + 1;
            $x = $a1.x * 2;
            $y = $a1.y * 3;
        } | {
            $n = 0;
            $x = 1;
            $y = 1;
        };

b returns [int n]:
    'b' b1=b {
            $n = $b1.n + 1;
        } | {
            $n = 0;
        };

c [int u, int l, int iuf, int ilf] returns [int slf, int suf, int m]:
    'c' c1=c [$u, $l, $iuf, $ilf] {
        $m = $c1.m + 1;
        $slf = $c1.slf + equals($l, $m);
        $suf = $c1.suf - equals($u, $c1.m);
    }
    | {
        $slf = $ilf;
        $suf = $iuf;
        $m = 0;
    };
