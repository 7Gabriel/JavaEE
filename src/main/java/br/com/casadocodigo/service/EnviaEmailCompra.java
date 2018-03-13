package br.com.casadocodigo.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.casadocodigo.daos.CompraDao;
import br.com.casadocodigo.infra.MailSender;
import br.com.casadocodigo.loja.models.Compra;

@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(
	        propertyName="destinationLookup",
	        propertyValue="java:/jms/topics/CarrinhoComprasTopico"),
	    @ActivationConfigProperty(
	        propertyName="destinationType",
	        propertyValue="javax.jms.Topic")
	})	
public class EnviaEmailCompra implements MessageListener {

	@Inject
	private MailSender email;
	
	@Inject
	private CompraDao compraDao;

	@Override
	public void onMessage(Message message) {

		try {
			TextMessage textMessage = (TextMessage) message;
			Compra compra = compraDao.buscarPorUUID(textMessage.getText());
			
			String emailBody = "Sua compra foi realizada com sucesso, com número de pedido " + compra.getuuid();
			email.send("joao1.gabrielsilva@gmail.com", compra.getUsuario().getEmail(), "Compra realizada com sucesso", emailBody);

		} catch (JMSException e) {
			e.printStackTrace();
		}	
		
			
	}
}
