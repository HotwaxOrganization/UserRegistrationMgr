package partyServices;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import java.sql.Timestamp;

public class ContactMechHelper {

    public static void createContactMechAndPurpose(Delegator delegator, String partyId, Timestamp now,
                                                   String info1, String info2, String info3,
                                                   String contactMechType, String purposeType,
                                                   String detailEntityName) throws GenericEntityException {
        if (info1 != null && !info1.isEmpty()) {
            String mechId = delegator.getNextSeqId("ContactMech");

            // Create ContactMech
            GenericValue contactMech = delegator.makeValue("ContactMech");
            contactMech.set("contactMechId", mechId);
            contactMech.set("contactMechTypeId", contactMechType);

            if ("EMAIL_ADDRESS".equals(contactMechType)) {
                contactMech.set("infoString", info1);
            }
            delegator.create(contactMech);

            // Create specific details if applicable
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

    public static void createPartyContactMech(Delegator delegator, String partyId, Timestamp now, String contactMechId)
            throws GenericEntityException {
        GenericValue partyContactMech = delegator.makeValue("PartyContactMech");
        partyContactMech.set("partyId", partyId);
        partyContactMech.set("contactMechId", contactMechId);
        partyContactMech.set("fromDate", now);
        delegator.create(partyContactMech);
    }
   public static void createPartyContactMechPurpose(Delegator delegator, String partyId, Timestamp now,
                                                     String contactMechId, String contactMechPurposeTypeId)
            throws GenericEntityException {
        GenericValue purpose = delegator.makeValue("PartyContactMechPurpose");
        purpose.set("partyId", partyId);
        purpose.set("contactMechId", contactMechId);
        purpose.set("fromDate", now);
        purpose.set("contactMechPurposeTypeId", contactMechPurposeTypeId);
        delegator.create(purpose);
    }
}
