package kpavlov.bank.services

import akka.actor.ActorSelection
import akka.actor.ActorSystem
import kpavlov.bank.domain.AccountId
import kpavlov.bank.domain.CustomerId


abstract class AbstractAkkaService(private val actorSystem: ActorSystem) {
    protected val TIMEOUT = 1_000L

    protected fun lookupCustomerActor(customerId: CustomerId): ActorSelection {
        return actorSystem.actorSelection("/user/customer-$customerId")
    }

    protected fun lookupCustomerAccountActor(customerId: CustomerId, accountId: AccountId): ActorSelection {
        return actorSystem.actorSelection("/user/customer-$customerId/account-$accountId")
    }
}