package partyServices;



import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilDateTime;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceUtil;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.apache.ofbiz.base.util.UtilMisc;
import java.util.List;


public class PartyServices {


    private static final String MODULE = PartyServices.class.getName();
    public static Map<String, Object> createPerson(DispatchContext ctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = new HashMap<>();


        Delegator delegator = ctx.getDelegator();
        Timestamp now = UtilDateTime.nowTimestamp();
      LocalDispatcher dispatcher=ctx.getDispatcher();

    String firstName=(String) context.get("firstName");
    String lastName=(String) context.get("lastName");
    String userLoginId=(String) context.get("userLoginId");
    String currentPassword=(String) context.get("currentPassword");


  //create a new party
        try {
        String partyId = delegator.getNextSeqId("Party");

            GenericValue newParty = delegator.makeValue("Party");
        newParty.set("partyId", partyId);
        newParty.set("partyTypeId", "PERSON");
        newParty.set("statusId", "PARTY_ENABLED");
        newParty.set("createdDate", now);
        delegator.create(newParty);

        GenericValue newPerson = delegator.makeValue("Person");
        newPerson.set("partyId", partyId);
        newPerson.set("firstName", firstName);
        newPerson.set("lastName", lastName);
        delegator.create(newPerson);

        GenericValue newUserLogin = delegator.makeValue("UserLogin");
        newUserLogin.set("userLoginId",userLoginId);
        newUserLogin.set("currentPassword",currentPassword);
        newUserLogin.set("partyId",partyId);
        delegator.create(newUserLogin);


        result.put("partyId",partyId);
        result.put("firstName",firstName);
        result.put("lastName",lastName);




        } catch (Exception e) {

            Debug.logWarning(e.getMessage(), MODULE);
        }


return result;

    }

        public static Map<String, Object> createPersonalInfo(DispatchContext ctx, Map<String, ? extends Object> context) {
            Map<String, Object> result = new HashMap<>();
            Delegator delegator = ctx.getDelegator();
            Timestamp now = UtilDateTime.nowTimestamp();

            // Retrieve form inputs
            String partyId = (String) context.get("partyId");
            String emailAddress = (String) context.get("emailAddress");
            String phoneNumber = (String) context.get("phoneNumber");
            String mobileNumber = (String) context.get("mobileNumber");
            String billingAddress = (String) context.get("billingAddress");
            String shippingAddress = (String) context.get("shippingAddress");


            try {
                // Create ContactMech for Email
                if (emailAddress != null && !emailAddress.isEmpty()) {
                    String emailMechId = delegator.getNextSeqId("ContactMech");

                    GenericValue emailMech = delegator.makeValue("ContactMech");
                    emailMech.set("contactMechId", emailMechId);
                    emailMech.set("contactMechTypeId", "EMAIL_ADDRESS");
                    emailMech.set("infoString", emailAddress);
                    delegator.create(emailMech);

                    GenericValue partyEmailMech = delegator.makeValue("PartyContactMech");
                    partyEmailMech.set("partyId", partyId);
                    partyEmailMech.set("contactMechId", emailMechId);
                    partyEmailMech.set("fromDate", now);
                    delegator.create(partyEmailMech);

                    GenericValue emailPurpose = delegator.makeValue("PartyContactMechPurpose");
                    emailPurpose.set("partyId", partyId);
                    emailPurpose.set("contactMechId", emailMechId);
                    emailPurpose.set("fromDate", now);
                    emailPurpose.set("contactMechPurposeTypeId", "PRIMARY_EMAIL");
                    delegator.create(emailPurpose);

                }

                // Create ContactMech for Phone Number
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    String phoneMechId = delegator.getNextSeqId("ContactMech");

                    GenericValue phoneMech = delegator.makeValue("ContactMech");
                    phoneMech.set("contactMechId", phoneMechId);
                    phoneMech.set("contactMechTypeId", "TELECOM_NUMBER");
                    delegator.create(phoneMech);

                    GenericValue telecomNumber = delegator.makeValue("TelecomNumber");
                    telecomNumber.set("contactMechId", phoneMechId);
                    telecomNumber.set("contactNumber", phoneNumber);
                    delegator.create(telecomNumber);

                    GenericValue partyPhoneMech = delegator.makeValue("PartyContactMech");
                    partyPhoneMech.set("partyId", partyId);
                    partyPhoneMech.set("contactMechId", phoneMechId);
                    partyPhoneMech.set("fromDate", now);
                    delegator.create(partyPhoneMech);

                    GenericValue phonePurpose = delegator.makeValue("PartyContactMechPurpose");
                    phonePurpose.set("partyId", partyId);
                    phonePurpose.set("contactMechId", phoneMechId);
                    phonePurpose.set("fromDate", now);
                    phonePurpose.set("contactMechPurposeTypeId", "PHONE_MOBILE");
                    delegator.create(phonePurpose);



                }

                // Create ContactMech for Mobile Number
                if (mobileNumber != null && !mobileNumber.isEmpty()) {
                    String mobileMechId = delegator.getNextSeqId("ContactMech");

                    GenericValue mobileMech = delegator.makeValue("ContactMech");
                    mobileMech.set("contactMechId", mobileMechId);
                    mobileMech.set("contactMechTypeId", "TELECOM_NUMBER");
                    delegator.create(mobileMech);

                    GenericValue mobileTelecomNumber = delegator.makeValue("TelecomNumber");
                    mobileTelecomNumber.set("contactMechId", mobileMechId);
                    mobileTelecomNumber.set("contactNumber", mobileNumber);
                    delegator.create(mobileTelecomNumber);

                    GenericValue partyMobileMech = delegator.makeValue("PartyContactMech");
                    partyMobileMech.set("partyId", partyId);
                    partyMobileMech.set("contactMechId", mobileMechId);
                    partyMobileMech.set("fromDate", now);
                    delegator.create(partyMobileMech);


                    GenericValue mobilePurpose = delegator.makeValue("PartyContactMechPurpose");
                    mobilePurpose.set("partyId", partyId);
                    mobilePurpose.set("contactMechId", mobileMechId);
                    mobilePurpose.set("fromDate", now);
                    mobilePurpose.set("contactMechPurposeTypeId", "PHONE_HOME");
                    delegator.create(mobilePurpose);





                }

                // Create ContactMech for Billing Address
                if (billingAddress != null && !billingAddress.isEmpty()) {
                    String billingMechId = delegator.getNextSeqId("ContactMech");

                    GenericValue billingMech = delegator.makeValue("ContactMech");
                    billingMech.set("contactMechId", billingMechId);
                    billingMech.set("contactMechTypeId", "POSTAL_ADDRESS");
                    delegator.create(billingMech);

                    GenericValue billingPostalAddress = delegator.makeValue("PostalAddress");
                    billingPostalAddress.set("contactMechId", billingMechId);
                    billingPostalAddress.set("address1", billingAddress);
                    delegator.create(billingPostalAddress);

                    GenericValue partyBillingMech = delegator.makeValue("PartyContactMech");
                    partyBillingMech.set("partyId", partyId);
                    partyBillingMech.set("contactMechId", billingMechId);
                    partyBillingMech.set("fromDate", now);
                    delegator.create(partyBillingMech);


                    GenericValue addressPurpose = delegator.makeValue("PartyContactMechPurpose");
                    addressPurpose.set("partyId", partyId);
                    addressPurpose.set("contactMechId", billingMechId);
                    addressPurpose.set("fromDate", now);
                    addressPurpose.set("contactMechPurposeTypeId", "BILLING_LOCATION");
                    delegator.create(addressPurpose);


                }

                // Create ContactMech for Shipping Address
                if (shippingAddress != null && !shippingAddress.isEmpty()) {
                    String shippingMechId = delegator.getNextSeqId("ContactMech");

                    GenericValue shippingMech = delegator.makeValue("ContactMech");
                    shippingMech.set("contactMechId", shippingMechId);
                    shippingMech.set("contactMechTypeId", "POSTAL_ADDRESS");
                    delegator.create(shippingMech);

                    GenericValue shippingPostalAddress = delegator.makeValue("PostalAddress");
                    shippingPostalAddress.set("contactMechId", shippingMechId);
                    shippingPostalAddress.set("address1", shippingAddress);
                    delegator.create(shippingPostalAddress);

                    GenericValue partyShippingMech = delegator.makeValue("PartyContactMech");
                    partyShippingMech.set("partyId", partyId);
                    partyShippingMech.set("contactMechId", shippingMechId);
                    partyShippingMech.set("fromDate", now);
                    delegator.create(partyShippingMech);

                    GenericValue addressPurpose = delegator.makeValue("PartyContactMechPurpose");
                    addressPurpose.set("partyId", partyId);
                    addressPurpose.set("contactMechId", shippingMechId);
                    addressPurpose.set("fromDate", now);
                    addressPurpose.set("contactMechPurposeTypeId", "SHIPPING_LOCATION");
                    delegator.create(addressPurpose);
                }

                result.put("partyId", partyId);

            } catch (GenericEntityException e) {
                Debug.logWarning(e.getMessage(), MODULE);
            }

            return result;

        }







    //Fetch By Party Id
        public static Map<String, Object> getPartyDetails(DispatchContext dctx, Map<String, ? extends Object> context) {
            Map<String, Object> result = new HashMap<>();
            String partyId = (String) context.get("partyId");
            Delegator delegator = dctx.getDelegator();

            try {
                GenericValue person = EntityQuery.use(delegator)
                        .from("Person")
                        .where("partyId", partyId)
                        .queryOne();

                if (person != null) {
                    result.put("partyId", person.get("partyId"));
                    result.put("firstName", person.get("firstName"));
                    result.put("lastName", person.get("lastName"));
                }

                GenericValue userLogin = EntityQuery.use(delegator)
                        .from("UserLogin")
                        .where("partyId", partyId)
                        .queryOne();

                if (userLogin != null) {
                    result.put("userLoginId", userLogin.get("userLoginId"));
                }

                List<GenericValue> contactMechs = EntityQuery.use(delegator)
                        .from("PartyContactMechPurpose")
                        .where("partyId", partyId)
                        .queryList();

                for (GenericValue pcmp : contactMechs) {

                    GenericValue contactMech = EntityQuery.use(delegator)
                            .from("ContactMech")
                            .where("contactMechId", pcmp.get("contactMechId"))
                            .queryOne();


                    if (contactMech != null) {
                        String purposeTypeId = (String) pcmp.get("contactMechPurposeTypeId");
                        switch (purposeTypeId) {
                            case "PRIMARY_EMAIL":
                                result.put("emailAddress", contactMech.get("infoString"));
                                break;
                            case "PHONE_MOBILE":
                                GenericValue mobileNumber = EntityQuery.use(delegator)
                                        .from("TelecomNumber")
                                        .where("contactMechId", pcmp.get("contactMechId"))
                                        .queryOne();
                                if (mobileNumber != null) {
                                    result.put("mobileNumber", mobileNumber.get("contactNumber"));
                                }
                                break;
                            case "PHONE_HOME":
                                GenericValue homeNumber = EntityQuery.use(delegator)
                                        .from("TelecomNumber")
                                        .where("contactMechId", pcmp.get("contactMechId"))
                                        .queryOne();
                                if (homeNumber != null) {
                                    result.put("homeNumber", homeNumber.get("contactNumber"));
                                }
                                break;
                            case "BILLING_LOCATION":
                                GenericValue billingAddress = EntityQuery.use(delegator)
                                        .from("PostalAddress")
                                        .where("contactMechId", pcmp.get("contactMechId"))
                                        .queryOne();
                                if (billingAddress != null) {
                                    result.put("billingAddress", billingAddress.get("address1"));
                                    result.put("bCity", billingAddress.get("city"));
                                    result.put("bPostalCode", billingAddress.get("postalCode"));

                                }
                                break;
                            case "SHIPPING_LOCATION":
                                GenericValue shippingAddress = EntityQuery.use(delegator)
                                        .from("PostalAddress")
                                        .where("contactMechId", pcmp.get("contactMechId"))
                                        .queryOne();
                                if (shippingAddress != null) {

                                    result.put("shippingAddress", shippingAddress.get("address1"));
                                    result.put("sCity", shippingAddress.get("city"));
                                    result.put("sPostalCode", shippingAddress.get("postalCode"));
                                }
                                break;
                        }
                    }
                }
            } catch (GenericEntityException e) {
                result.put("error", "An error occurred: " + e.getMessage());
            }


        return result;

    }




        public static Map<String, Object> createPersonalInfoTest(DispatchContext ctx, Map<String, ? extends Object> context) {
            Map<String, Object> result = new HashMap<>();
            Delegator delegator = ctx.getDelegator();
            Timestamp now = UtilDateTime.nowTimestamp();

            String partyId = (String) context.get("partyId");
            String emailAddress = (String) context.get("emailAddress");
            String phoneNumber = (String) context.get("phoneNumber");
            String mobileNumber = (String) context.get("mobileNumber");
            String billingAddress = (String) context.get("bAddress");
            String shippingAddress = (String) context.get("sAddress");
            String bCity = (String) context.get("bCity");
            String sCity = (String) context.get("sCity");
            String bPostalCode = (String) context.get("bPostalCode");
            String sPostalCode = (String) context.get("sPostalCode");


            try {
                createContactMechAndPurpose(delegator, partyId, now, emailAddress,null,null, "EMAIL_ADDRESS", "PRIMARY_EMAIL", null);
                createContactMechAndPurpose(delegator, partyId, now, phoneNumber, null,null, "TELECOM_NUMBER", "PHONE_MOBILE", "TelecomNumber");
                createContactMechAndPurpose(delegator, partyId, now, mobileNumber,null,null, "TELECOM_NUMBER", "PHONE_HOME", "TelecomNumber");
                createContactMechAndPurpose(delegator, partyId, now, billingAddress,bCity,bPostalCode, "POSTAL_ADDRESS", "BILLING_LOCATION", "PostalAddress");
                createContactMechAndPurpose(delegator, partyId, now, shippingAddress,sCity,sPostalCode, "POSTAL_ADDRESS", "SHIPPING_LOCATION", "PostalAddress");

                result.put("partyId", partyId);

            } catch (GenericEntityException e) {
                Debug.logWarning(e.getMessage(), MODULE);
            }

            return result;
        }

        private static void createContactMechAndPurpose(Delegator delegator, String partyId, Timestamp now, String info1,String info2,String info3, String contactMechType, String purposeType, String detailEntityName) throws GenericEntityException {
            if (info1 != null && !info1.isEmpty()) {
                String mechId = delegator.getNextSeqId("ContactMech");

                GenericValue contactMech = delegator.makeValue("ContactMech");
                contactMech.set("contactMechId", mechId);
                contactMech.set("contactMechTypeId", contactMechType);
                if ("EMAIL_ADDRESS".equals(contactMechType)) {
                    contactMech.set("infoString", info1);
                }
                delegator.create(contactMech);

                if (detailEntityName != null) {
                    GenericValue detailValue = delegator.makeValue(detailEntityName);
                    detailValue.set("contactMechId", mechId);
                    if ("PostalAddress".equals(detailEntityName)) {
                        detailValue.set("address1", info1);
                        detailValue.set("city", info2);
                        detailValue.set("postalCode", info3);


                    } else if ("TelecomNumber".equals(detailEntityName)) {
                        detailValue.set("contactNumber", info1);
                    }
                    delegator.create(detailValue);
                }


                createPartyContactMech(delegator, partyId, now, mechId);
                createPartyContactMechPurpose(delegator, partyId, now, mechId, purposeType);
            }
        }

        private static void createPartyContactMech(Delegator delegator, String partyId, Timestamp now, String contactMechId) throws GenericEntityException {
            GenericValue partyContactMech = delegator.makeValue("PartyContactMech");
            partyContactMech.set("partyId", partyId);
            partyContactMech.set("contactMechId", contactMechId);
            partyContactMech.set("fromDate", now);
            delegator.create(partyContactMech);
        }

        private static void createPartyContactMechPurpose(Delegator delegator, String partyId, Timestamp now, String contactMechId, String contactMechPurposeTypeId) throws GenericEntityException {
            GenericValue purpose = delegator.makeValue("PartyContactMechPurpose");
            purpose.set("partyId", partyId);
            purpose.set("contactMechId", contactMechId);
            purpose.set("fromDate", now);
            purpose.set("contactMechPurposeTypeId", contactMechPurposeTypeId);
            delegator.create(purpose);
        }




    public static Map<String, Object> createPersonalInfoTest2(DispatchContext ctx, Map<String, ? extends Object> context) {
        Delegator delegator = ctx.getDelegator();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> result = new HashMap<>();

        String partyId = (String) context.get("partyId");
        String email = (String) context.get("emailAddress");
        String phone = (String) context.get("phoneNumber");
        String mobile = (String) context.get("mobileNumber");
        String billingStreet = (String) context.get("bAddress");
        String billingCity = (String) context.get("bCity");
        String billingPostal = (String) context.get("bPostalCode");
        String shippingStreet = (String) context.get("sAddress");
        String shippingCity = (String) context.get("sCity");
        String shippingPostal = (String) context.get("sPostalCode");

        try {
            ContactMechHelper.createContactMechAndPurpose(delegator, partyId, now, email, null, null, "EMAIL_ADDRESS", "PRIMARY_EMAIL", null);
            ContactMechHelper.createContactMechAndPurpose(delegator, partyId, now, phone, null, null, "TELECOM_NUMBER", "PHONE_HOME", "TelecomNumber");
            ContactMechHelper.createContactMechAndPurpose(delegator, partyId, now, mobile, null, null, "TELECOM_NUMBER", "PHONE_MOBILE", "TelecomNumber");
            ContactMechHelper.createContactMechAndPurpose(delegator, partyId, now, billingStreet, billingCity, billingPostal, "POSTAL_ADDRESS", "BILLING_LOCATION", "PostalAddress");
            ContactMechHelper.createContactMechAndPurpose(delegator, partyId, now, shippingStreet, shippingCity, shippingPostal, "POSTAL_ADDRESS", "SHIPPING_LOCATION", "PostalAddress");

            result.put("partyId", partyId);
        } catch (GenericEntityException e) {
            e.printStackTrace();
        }

        return result;
    }


}



