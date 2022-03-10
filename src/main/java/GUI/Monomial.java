package GUI;

public class Monomial {
    double coeficient;
    int grad;

    Monomial() {
        coeficient = 0;
        grad = 0;
    }

    public Monomial(double coeficient, int grad){
        this.coeficient = coeficient;
        this.grad = grad;
    }

    public Monomial divide(Monomial that){
        return new Monomial(this.coeficient / that.coeficient, this.grad - that.grad);
    }
}
