package br.com.phzero.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.request.ApiDateRequest;
import br.com.moip.request.CreditCardRequest;
import br.com.moip.request.CustomerRequest;
import br.com.moip.request.FundingInstrumentRequest;
import br.com.moip.request.HolderRequest;
import br.com.moip.request.OrderAmountRequest;
import br.com.moip.request.OrderRequest;
import br.com.moip.request.PaymentRequest;
import br.com.moip.request.PhoneRequest;
import br.com.moip.request.ShippingAddressRequest;
import br.com.moip.request.SubtotalsRequest;
import br.com.moip.request.TaxDocumentRequest;
import br.com.moip.resource.Order;
import br.com.moip.resource.Payment;
import br.com.phzero.commerce.model.ItemDePedido;
import br.com.phzero.commerce.model.Pagamento;
import br.com.phzero.commerce.model.Pedido;

@Service
public class CheckoutService {

	@Autowired
	private Client client;

	public Pedido criarPedido(Pedido pedido) {
		API api = new API(client);
		
		SubtotalsRequest subtotals = new SubtotalsRequest()
		  .discount(pedido.getDesconto())
		  .addiction(pedido.getAcrescimo());
		
		/** TODO addiction parece que não está funcionando */
		
		OrderRequest orderRequest = new OrderRequest()
				  .ownId(pedido.getId().toString())
				  .amount(new OrderAmountRequest()
						  .currency("BRL")
						  .subtotals(subtotals))
				  .customer(new CustomerRequest()
				    .ownId(pedido.getCliente().getId().toString())
				    .fullname(pedido.getCliente().getNome())
				    .email(pedido.getCliente().getEmail())
				    .birthdate(new ApiDateRequest().date(pedido.getCliente().getDataNascimento()))
				    .taxDocument(TaxDocumentRequest.cpf(pedido.getCliente().getCpf()))
				    .phone(new PhoneRequest().setAreaCode("11").setNumber(pedido.getCliente().getTelefone()))
				    .shippingAddressRequest(new ShippingAddressRequest().street(pedido.getCliente().getEndereco().getNome())
				      .streetNumber(pedido.getCliente().getEndereco().getNumero())
				      .complement(pedido.getCliente().getEndereco().getComplemento())
				      .city(pedido.getCliente().getEndereco().getCidade())
				      .state(pedido.getCliente().getEndereco().getEstado())
				      .district(pedido.getCliente().getEndereco().getBairro())
				      .country("BRA")
				      .zipCode(pedido.getCliente().getEndereco().getCep())
				    ));
		
		for (ItemDePedido item: pedido.getItens()) {
			orderRequest.addItem(item.getItem().getNome(), 
					item.getQuantidade(),
					item.getItem().getDetalhes(), 
					item.getItem().getPreco());
		}

		Order createdOrder = api.order().create(orderRequest);
		
		pedido.setMoipId(createdOrder.getId());
		
		return pedido;
	}
	
	
	
	
	public Pagamento criarPagamento(Pagamento pagamento) {
		API api = new API(client);
	
		Payment createdPayment = api.payment().create(new PaymentRequest()
				  .orderId(pagamento.getPedido().getMoipId())
				  .installmentCount(pagamento.getNumeroParcelas())
				  .fundingInstrument(new FundingInstrumentRequest()
				    .creditCard(new CreditCardRequest()
				      .hash(pagamento.getHashCartao())
				      .holder(new HolderRequest()
				        .fullname(pagamento.getPedido().getCliente().getNome())
				        .birthdate("1988-10-10")
				        .phone(new PhoneRequest().setAreaCode("11").setNumber(pagamento.getPedido().getCliente().getTelefone()))
				        .taxDocument(TaxDocumentRequest.cpf(pagamento.getPedido().getCliente().getCpf()))
				      )
				    )
				  )
				);
		
		pagamento.setMoipId(createdPayment.getId());
		pagamento.setStatus("CREATED");
		
		return pagamento;
	}
}


















