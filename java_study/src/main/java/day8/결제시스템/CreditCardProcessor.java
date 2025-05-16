package day8.결제시스템;

public class CreditCardProcessor implements PaymentProcessor{
    private final PaymentValidator paymentValidator;

    private final PaymentGateway paymentGateway;

    public CreditCardProcessor(PaymentValidator paymentValidator, PaymentGateway paymentGateway) {
        this.paymentValidator = paymentValidator;
        this.paymentGateway = paymentGateway;
    }


    @Override
    public void processPayment(double payment) throws PaymentValidationException {
        if(!paymentValidator.validatePayment(payment)){
            paymentGateway.processPayment(payment);
        }else{
            throw new PaymentValidationException("잘못된 결제입니다");
        }
    }
}
