package GUI;

import java.util.*;

public class Polynomial {
    LinkedList<Monomial> monomials;

    Polynomial(){
        monomials = new LinkedList<>();
    }

    public LinkedList<Monomial> getMonomials(){return monomials;}

    public Polynomial add(Polynomial that) {
        Polynomial result = new Polynomial();
        ListIterator<Monomial> thisIterator = this.monomials.listIterator(0);
        ListIterator<Monomial> thatIterator = that.monomials.listIterator(0);
        Monomial thisMonomial = thisIterator.next();
        Monomial thatMonomial = thatIterator.next();
        Monomial monomial;
        int who = 0; // 1 - this mai are elemente
                     // 2 - that mai are elemente
                     // 3 - niciunul
                     // 0 - amandoi
        while (true) {
            if (thisMonomial.grad < thatMonomial.grad) {
                monomial = new Monomial(thisMonomial.coeficient, thisMonomial.grad);
                result.monomials.add(monomial);
                if (thisIterator.hasNext())
                    thisMonomial = thisIterator.next();
                else{
                    who = 2;
                    break;
                }
            } else if (thisMonomial.grad > thatMonomial.grad) {
                monomial = new Monomial(thatMonomial.coeficient, thatMonomial.grad);
                result.monomials.add(monomial);
                if (thatIterator.hasNext())
                    thatMonomial = thatIterator.next();
                else {
                    who = 1;
                    break;
                }
            } else {
                if (thisMonomial.coeficient + thatMonomial.coeficient != 0) { // daca au graduri egale intreb daca suma coeficientilor este diferita de 0, altfel se reduc termenii
                    monomial = new Monomial(thisMonomial.coeficient + thatMonomial.coeficient, thatMonomial.grad);
                    result.monomials.add(monomial);
                }
                if (thisIterator.hasNext() && thatIterator.hasNext()){
                    thisMonomial = thisIterator.next();
                    thatMonomial = thatIterator.next();
                }
                else if(!(thisIterator.hasNext() || thatIterator.hasNext())){
                    who = 3;
                    break;
                }
                else if(!thisIterator.hasNext() && thatIterator.hasNext()){
                    thatMonomial = thatIterator.next();
                    who = 2;
                    break;
                }
                else if(!thatIterator.hasNext() && thisIterator.hasNext()){
                    thisMonomial = thisIterator.next();
                    who = 1;
                    break;
                }
            }
        }

        if (!thisIterator.hasNext() && who == 2) { // daca that are elemente
            while (true) {
                monomial = new Monomial(thatMonomial.coeficient, thatMonomial.grad);
                result.monomials.add(monomial);
                if (thatIterator.hasNext())
                    thatMonomial = thatIterator.next();
                else break;
            }
        } else if (!thatIterator.hasNext() && who == 1) { // daca this are elemente
            while (true) {
                monomial = new Monomial(thisMonomial.coeficient, thisMonomial.grad);
                result.monomials.add(monomial);
                if (thisIterator.hasNext())
                    thisMonomial = thisIterator.next();
                else break;
            }
        }
        return result;
    }

    public Polynomial subtract(Polynomial that) {
        Polynomial result = new Polynomial();
        ListIterator<Monomial> thisIterator = this.monomials.listIterator(0);
        ListIterator<Monomial> thatIterator = that.monomials.listIterator(0);
        Monomial thisMonomial = thisIterator.next();
        Monomial thatMonomial = thatIterator.next();
        Monomial monomial;
        int who = 0; // 1 - this mai are elemente
        // 2 - that mai are elemente
        // 3 - niciunul
        // 0 - amandoi
        while (true) {
            if (thisMonomial.grad < thatMonomial.grad) {
                monomial = new Monomial(thisMonomial.coeficient, thisMonomial.grad);
                result.monomials.add(monomial);
                if (thisIterator.hasNext())
                    thisMonomial = thisIterator.next();
                else{
                    who = 2;
                    break;
                }
            } else if (thisMonomial.grad > thatMonomial.grad) {
                monomial = new Monomial(-thatMonomial.coeficient, thatMonomial.grad);
                result.monomials.add(monomial);
                if (thatIterator.hasNext())
                    thatMonomial = thatIterator.next();
                else {
                    who = 1;
                    break;
                }
            } else {
                if (thisMonomial.coeficient - thatMonomial.coeficient != 0) { // daca au graduri egale intreb daca suma coeficientilor este diferita de 0, altfel se reduc termenii
                    monomial = new Monomial(thisMonomial.coeficient - thatMonomial.coeficient, thatMonomial.grad);
                    result.monomials.add(monomial);
                }
                if (thisIterator.hasNext() && thatIterator.hasNext()){
                    thisMonomial = thisIterator.next();
                    thatMonomial = thatIterator.next();
                }
                else if(!(thisIterator.hasNext() || thatIterator.hasNext())){
                    who = 3;
                    break;
                }
                else if(!thisIterator.hasNext() && thatIterator.hasNext()){
                    thatMonomial = thatIterator.next();
                    who = 2;
                    break;
                }
                else if(!thatIterator.hasNext() && thisIterator.hasNext()){
                    thisMonomial = thisIterator.next();
                    who = 1;
                    break;
                }
            }
        }

        if (!thisIterator.hasNext() && who == 2) { // daca that are elemente
            while (true) {
                monomial = new Monomial(-thatMonomial.coeficient, thatMonomial.grad);
                result.monomials.add(monomial);
                if (thatIterator.hasNext())
                    thatMonomial = thatIterator.next();
                else break;
            }
        } else if (!thatIterator.hasNext() && who == 1) { // daca this are elemente
            while (true) {
                monomial = new Monomial(thisMonomial.coeficient, thisMonomial.grad);
                result.monomials.add(monomial);
                if (thisIterator.hasNext())
                    thisMonomial = thisIterator.next();
                else break;
            }
        }
        return result;
    }

    public Polynomial derive(){
        Polynomial result = new Polynomial();
        Monomial thisMonomial;
        ListIterator<Monomial> thisIterator = this.monomials.listIterator(0);
        while(thisIterator.hasNext()){
            thisMonomial = thisIterator.next();
            if(thisMonomial.grad > 0)
                result.monomials.add(new Monomial(thisMonomial.coeficient * thisMonomial.grad, thisMonomial.grad - 1));
        }
        return result;
    }

    public Polynomial integrate(){
        Polynomial result = new Polynomial();
        Monomial thisMonomial;
        ListIterator<Monomial> thisIterator = this.monomials.listIterator(0);
        while(thisIterator.hasNext()){
            thisMonomial = thisIterator.next();
            result.monomials.add(new Monomial(thisMonomial.coeficient / (thisMonomial.grad + 1), thisMonomial.grad + 1));
        }
        return result;
    }

    public Polynomial multiply(Polynomial that){
        Polynomial result = new Polynomial();
        ListIterator<Monomial> thisIterator = this.monomials.listIterator(0);
        ListIterator<Monomial> thatIterator = that.monomials.listIterator(0);
        Monomial thisMonomial, thatMonomial, resultMonomial, resultIteratorMonomial;
        boolean broke = false;
        while(thisIterator.hasNext()){
            thisMonomial = thisIterator.next();
            while(thatIterator.hasNext()){
                thatMonomial = thatIterator.next();
                resultMonomial = new Monomial(thisMonomial.coeficient * thatMonomial.coeficient, thisMonomial.grad + thatMonomial.grad);
                ListIterator<Monomial> resultIterator = result.monomials.listIterator(0);
                if(result.monomials.size() == 0){
                    result.monomials.add(resultMonomial);
                }
                else {
                    while (resultIterator.hasNext()) {
                        resultIteratorMonomial = resultIterator.next();
                        if (resultMonomial.grad == resultIteratorMonomial.grad) {
                            resultIteratorMonomial.coeficient += resultMonomial.coeficient;
                            broke = true;
                            break;
                        } else if (resultMonomial.grad < resultIteratorMonomial.grad) {
                            result.monomials.add(result.monomials.indexOf(resultIteratorMonomial), resultMonomial);
                            broke = true;
                            break;
                        }
                    }
                    if(!broke){
                        result.monomials.add(resultMonomial);
                    }
                    broke = false;
                }
            }
            thatIterator = that.monomials.listIterator(0);
        }
        return result;
    }

    public Polynomial[] divide(Polynomial that){
        Polynomial[] result = new Polynomial[2];

        if(this.monomials.getLast().grad < that.monomials.getLast().grad) {
            result[0] = null;
            result[1] = this.clone();
            return result;
        }

        Polynomial quotient = new Polynomial();
        Polynomial monomialPolynomial = new Polynomial();
        Polynomial dividend = this.clone();
        Monomial resultMonomial;

        while(dividend.monomials.getLast().grad >= that.monomials.getLast().grad && !dividend.monomials.isEmpty()){
            resultMonomial = dividend.monomials.getLast().divide(that.monomials.getLast());
            quotient.monomials.addFirst(resultMonomial);
            monomialPolynomial.monomials.add(quotient.monomials.getFirst());
            dividend = dividend.subtract(that.multiply(monomialPolynomial));
            monomialPolynomial.monomials.remove();
        }

        if(dividend.monomials.isEmpty()){
            result[1] = null;
        }
        else{
            result[1] = dividend;
        }
        result[0] = quotient;
        return result;
    }

    @Override
    public String toString(){
        String string = "";
        ListIterator<Monomial> iterator = this.monomials.listIterator(monomials.indexOf(monomials.getLast()) + 1);
        Monomial monomial;
        if (iterator.hasPrevious()) {
            monomial = iterator.previous();
            string = string.concat((int)monomial.coeficient + "x^" + monomial.grad);
            while (iterator.hasPrevious()) {
                monomial = iterator.previous();
                if (monomial.coeficient >= 0)
                    string = string.concat("+" + (int)monomial.coeficient + "x^" + monomial.grad);
                else
                    string = string.concat("-" + (int)-monomial.coeficient + "x^" + monomial.grad);
            }
        } else {
            string = null;
        }
        return string;
    }

    public String doubleToString(){
        String string = "";
        ListIterator<Monomial> iterator = this.monomials.listIterator(monomials.indexOf(monomials.getLast()) + 1);
        Monomial monomial;
        if (iterator.hasPrevious()) {
            monomial = iterator.previous();
            string = string.concat(Controller.format(monomial.coeficient) + "x^" + monomial.grad);
            while (iterator.hasPrevious()) {
                monomial = iterator.previous();
                if (monomial.coeficient >= 0) {
                    string = string.concat("+" + Controller.format(monomial.coeficient) + "x^" + monomial.grad);
                }
                else {
                    string = string.concat("-" + Controller.format(-monomial.coeficient) + "x^" + monomial.grad);
                }
            }
        } else {
            string = null;
        }
        return string;
    }

    @Override
    public Polynomial clone(){
        Polynomial clone = new Polynomial();
        ListIterator<Monomial> thisIterator = this.monomials.listIterator(0);
        Monomial monomial;
        while(thisIterator.hasNext()){
            monomial = thisIterator.next();
            clone.monomials.addLast(monomial);
        }
        return clone;
    }
}
