package BookModel;

import BookModel.impl.BookModelFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.EvlContext;

import java.util.Collection;
import java.util.Map;

public class EVLRunner {

    public static void main(String[] args) {

        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("test", new XMIResourceFactoryImpl());

        String modelPath = "src/main/resources/metamodel/MyModel";

        EvlModule evlModule = new EvlModule();
        try {
            evlModule.parse(EVLRunner.class.getResource("/evl/Validate.evl").toURI());

            if (evlModule.getParseProblems().size() > 0) {
                System.err.println("Parse errors occured...");
                for (ParseProblem problem : evlModule.getParseProblems()) {
                    System.err.println(problem.toString());
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < 5; i++) {
            // create a resource
            ResourceSet resSet = new ResourceSetImpl();
            Resource resource = resSet.createResource(URI.createURI(modelPath + i + ".test"));

            // Reset context
            EvlContext context = new EvlContext();
            evlModule.setContext(context);
            context.setModule(evlModule);

            // Add to model
            BookModelFactory factory = new BookModelFactoryImpl();
            Book book = factory.createBook();
            book.setBookId(i);
            resource.getContents().add(book);

            InMemoryEmfModel model = new InMemoryEmfModel(resource);
            evlModule.getContext().getModelRepository().addModel(model);

            try {
                evlModule.execute();
            } catch (EolRuntimeException e) {
                e.printStackTrace();
            }

            Collection<UnsatisfiedConstraint> unsatisfied = evlModule.getContext().getUnsatisfiedConstraints();

            if (unsatisfied.size() > 0) {
                System.out.println(unsatisfied.size() + " constraint(s) have not been satisfied");
                for (UnsatisfiedConstraint uc : unsatisfied) {
                    System.out.println(uc.getMessage());
                }
            } else {
                System.out.println("All constraints have been satisfied");
            }
        }
    }
}
