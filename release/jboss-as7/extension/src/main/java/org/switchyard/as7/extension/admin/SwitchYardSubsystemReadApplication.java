/*
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.switchyard.as7.extension.admin;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.NAME;

import javax.xml.namespace.QName;

import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.OperationStepHandler;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceController;
import org.switchyard.admin.Application;
import org.switchyard.admin.SwitchYard;
import org.switchyard.as7.extension.services.SwitchYardAdminService;

/**
 * SwitchYardSubsystemReadApplication
 * 
 * Operation returning details of the applications deployed on the SwitchYard
 * subsystem.
 * 
 * @author Rob Cernich
 */
public final class SwitchYardSubsystemReadApplication implements OperationStepHandler {

    /**
     * The global instance for this operation.
     */
    public static final SwitchYardSubsystemReadApplication INSTANCE = new SwitchYardSubsystemReadApplication();

    private SwitchYardSubsystemReadApplication() {
        // forbidden inheritance
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jboss.as.controller.OperationStepHandler#execute(org.jboss.as.controller
     * .OperationContext, org.jboss.dmr.ModelNode)
     */
    @Override
    public void execute(final OperationContext context, final ModelNode operation) throws OperationFailedException {

        context.addStep(new OperationStepHandler() {
            @Override
            public void execute(final OperationContext context, final ModelNode operation)
                    throws OperationFailedException {
                final ServiceController<?> controller = context.getServiceRegistry(false).getRequiredService(
                        SwitchYardAdminService.SERVICE_NAME);

                final ModelNode applications = context.getResult();
                SwitchYard switchYard = SwitchYard.class.cast(controller.getService().getValue());
                if (operation.hasDefined(NAME)) {
                    final QName applicationName = QName.valueOf(operation.get(NAME).asString());
                    final Application application = switchYard.getApplication(applicationName);
                    if (application != null) {
                        applications.add(ModelNodeCreationUtil.createApplicationNode(application));
                    }
                } else {
                    for (Application application : switchYard.getApplications()) {
                        applications.add(ModelNodeCreationUtil.createApplicationNode(application));
                    }
                }
                context.stepCompleted();
            }
        }, OperationContext.Stage.RUNTIME);
        context.stepCompleted();
    }
}
