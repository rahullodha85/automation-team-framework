package tests.commonplatform.checkoutunregistered;

/**
 * Created by vevinmoza on 3/26/16.
 */
public class Scen50 {
}
/*
	SearchFunction.SearchFor(dataObject.getSkuListInfo().get(0));
		pdp.AddToBag(1);
		SearchFunction.SearchFor(dataObject.getSkuListInfo().get(1));
		pdp.AddToBag(1);
		pdp.EnterBag();
		bag.ClickCheckout().LoginWith("saksqa148@saks.com");
		shp.AddShippingAddress(false, dataObject.getAddress1(), dataObject.getState(), dataObject.getCity(), dataObject.getZipCode(), false);
		shp.ClickCheckoutOptionalAddressConfirmation();
		rsp.ClickShippingAndBillingTab();
		shp.ClickEditBillingAddress();
		shp.AddBillingAddress(dataObject.getAddress1(), dataObject.getState(),
				dataObject.getCity(), dataObject.getZipCode(), false);
		pap.ClickCheckout();
		pap.AddNewCreditCard(3);//mastercard
		pap.ClickCheckout(); // REVISIT
		rsp.ClickPlaceOrder();
		conf.CreateAccount();
		conf.GoToYourAccount();
		ValidateYourAcc.ShippingAddressContains(Storage.get("shippingAddress"));
		ValidateYourAcc.BillingAddressContains(Storage.get("billingAddress"));
		ValidateYourAcc.CreditCardContains(Storage.get("ccNumber"));
 */