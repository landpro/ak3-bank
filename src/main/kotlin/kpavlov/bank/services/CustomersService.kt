package kpavlov.bank.services

import akka.actor.ActorSystem
import akka.pattern.PatternsCS
import kpavlov.bank.api.CustomersApi
import kpavlov.bank.domain.CustomerDetails
import kpavlov.bank.domain.CustomerId
import kpavlov.bank.services.actors.GetCustomerDetailsCommand
import java.util.concurrent.CompletionStage


class CustomersService(actorSystem: ActorSystem) : AbstractAkkaService(actorSystem), CustomersApi {

    override fun getCustomerDetails(customerId: CustomerId): CompletionStage<CustomerDetails> {
        val actorSelection = lookupCustomerActor(customerId)
        @Suppress("UNCHECKED_CAST")
        return PatternsCS.ask(actorSelection, GetCustomerDetailsCommand(), TIMEOUT) as CompletionStage<CustomerDetails>
    }
}
