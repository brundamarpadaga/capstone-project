'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">analytics-dashboard-frontend documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-bs-toggle="collapse" ${ isNormalMode ?
                                'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-AppModule-69ffb35804303a02baf502c35607dc2cd5ebec3cbc31b4496314814ed47989d440aa3372196d70e99dbfe93056e4e70c3ebf6e90cae7213b8b71d38a2849e1aa"' : 'data-bs-target="#xs-components-links-module-AppModule-69ffb35804303a02baf502c35607dc2cd5ebec3cbc31b4496314814ed47989d440aa3372196d70e99dbfe93056e4e70c3ebf6e90cae7213b8b71d38a2849e1aa"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-69ffb35804303a02baf502c35607dc2cd5ebec3cbc31b4496314814ed47989d440aa3372196d70e99dbfe93056e4e70c3ebf6e90cae7213b8b71d38a2849e1aa"' :
                                            'id="xs-components-links-module-AppModule-69ffb35804303a02baf502c35607dc2cd5ebec3cbc31b4496314814ed47989d440aa3372196d70e99dbfe93056e4e70c3ebf6e90cae7213b8b71d38a2849e1aa"' }>
                                            <li class="link">
                                                <a href="components/AddCallRecordComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AddCallRecordComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AddPlanComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AddPlanComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AddSmsRecordComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AddSmsRecordComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CallRecordsChartComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CallRecordsChartComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CallRecordsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CallRecordsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DashboardComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >DashboardComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DataUsageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >DataUsageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/EditPlanComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >EditPlanComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/PlansComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >PlansComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SmsRecordsChartComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SmsRecordsChartComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SmsRecordsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SmsRecordsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SubscriberUsageDetailsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SubscriberUsageDetailsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SubscribersComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SubscribersComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link" >AppRoutingModule</a>
                            </li>
                </ul>
                </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/AnalyticsService.html" data-type="entity-link" >AnalyticsService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/CallRecordsService.html" data-type="entity-link" >CallRecordsService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/DataService.html" data-type="entity-link" >DataService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/PlansService.html" data-type="entity-link" >PlansService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/SmsRecordsService.html" data-type="entity-link" >SmsRecordsService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/SubscribersService.html" data-type="entity-link" >SubscribersService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UsageService.html" data-type="entity-link" >UsageService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/WebSocketService.html" data-type="entity-link" >WebSocketService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interfaces-links"' :
                            'data-bs-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/CallRecord.html" data-type="entity-link" >CallRecord</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/DataLeftDto.html" data-type="entity-link" >DataLeftDto</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/LocationBasedPricing.html" data-type="entity-link" >LocationBasedPricing</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Plan.html" data-type="entity-link" >Plan</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/PlanDTO.html" data-type="entity-link" >PlanDTO</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/SmsRecord.html" data-type="entity-link" >SmsRecord</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Status.html" data-type="entity-link" >Status</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Subscriber.html" data-type="entity-link" >Subscriber</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/SubscriberAnalyticsDto.html" data-type="entity-link" >SubscriberAnalyticsDto</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/SubscribersResponse.html" data-type="entity-link" >SubscribersResponse</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/SubscriberUsage.html" data-type="entity-link" >SubscriberUsage</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});