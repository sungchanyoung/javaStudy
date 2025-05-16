package day8.결제시스템;

public class improvedCreditCardProcessor  implements PaymentProcessor{

    private final PaymentValidator paymentValidator;
    private final ImprovedPaymentGateway paymentGateway;

    public improvedCreditCardProcessor(PaymentValidator paymentValidator, ImprovedPaymentGateway paymentGateway) {
        this.paymentValidator = paymentValidator;
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void processPayment(double payment) throws PaymentValidationException {
        if (paymentValidator.validatePayment(payment)) {
            boolean success =  paymentGateway.processPayment(payment);
            if(!success){
                throw new PaymentValidationException("Payment processing failed");
            }
        }else{
            throw new PaymentValidationException("Invalid payment");
        }
    }
}
