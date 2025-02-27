





partyId = parameters.partyId




person = from("Person")
                .where("partyId", partyId)
                .queryOne()

if (person) {
    context.lastName=person.lastName;
    context.firstName=person.firstName;
    context.partyId=person.partyId;



        }

         userLogin = from("UserLogin")
                .where("partyId", partyId)
                .queryOne()

        if (userLogin) {
            context.userLoginId=userLogin.userLoginId;
        }

        contactMechs = from("PartyContactMechPurpose")
                .where("partyId", partyId)
                .queryList()

        contactMechs.each { pcmp ->
             contactMech = from("ContactMech")
                    .where("contactMechId", pcmp.get("contactMechId"))
                    .queryOne()

            if (contactMech) {
                purposeTypeId = pcmp.get("contactMechPurposeTypeId")
                switch (purposeTypeId) {
                    case "PRIMARY_EMAIL":
                        context.emailAddress=contactMech.infoString
                        break
                    case "PHONE_MOBILE":
                         mobileNumber = from("TelecomNumber")
                                .where("contactMechId", pcmp.get("contactMechId"))
                                .queryOne()
                        if (mobileNumber) {
                            context.mobileNumber=mobileNumber.contactNumber
                        }
                        break
                    case "PHONE_HOME":
                         homeNumber = from("TelecomNumber")
                                .where("contactMechId", pcmp.get("contactMechId"))
                                .queryOne()
                        if (homeNumber) {
                            context.homeNumber=homeNumber.contactNumber
                        }
                        break
                    case "BILLING_LOCATION":
                           billingAddress = from("PostalAddress")
                                .where("contactMechId", pcmp.get("contactMechId"))
                                .queryOne()
                        if (billingAddress) {
                            context.billingAddress=billingAddress.address1
                            context.bCity=billingAddress.city
                            context.bPostalCode=billingAddress.postalCode


                        }
                        break
                    case "SHIPPING_LOCATION":
                         shippingAddress = from("PostalAddress")
                                .where("contactMechId", pcmp.get("contactMechId"))
                                .queryOne()
                        if (shippingAddress) {
                        context.shippingAddress=shippingAddress.address1
                        context.sCity=shippingAddress.city
                        context.sPostalCode=shippingAddress.postalCode

                        }
                        break
                }
            }
        }













