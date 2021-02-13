package io.github.pascalgrimaud.crm;

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
            .importPackages("io.github.pascalgrimaud.crm");

        noClasses()
            .that()
            .resideInAnyPackage("io.github.pascalgrimaud.crm.service..")
            .or()
            .resideInAnyPackage("io.github.pascalgrimaud.crm.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..io.github.pascalgrimaud.crm.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
