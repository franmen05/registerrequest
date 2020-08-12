package com.losrosantes.registerrequest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.losrosantes.registerrequest");

        noClasses()
            .that()
            .resideInAnyPackage("com.losrosantes.registerrequest.service..")
            .or()
            .resideInAnyPackage("com.losrosantes.registerrequest.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.losrosantes.registerrequest.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
