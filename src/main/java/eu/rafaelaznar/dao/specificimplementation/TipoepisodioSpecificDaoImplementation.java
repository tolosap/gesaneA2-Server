package eu.rafaelaznar.dao.specificimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.dao.genericimplementation.TableGenericDaoImplementation;
import java.sql.Connection;

public class TipoepisodioSpecificDaoImplementation extends TableGenericDaoImplementation {

    public TipoepisodioSpecificDaoImplementation(Connection oPooledConnection, MetaBeanHelper oPuserBean_security, String strWhere) throws Exception {
        super("tipoepisodio", oPooledConnection, oPuserBean_security, strWhere);
    }

}
