/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.ast.statement;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLObjectImpl;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLAlterTableAddColumn extends SQLObjectImpl implements SQLAlterTableItem {
    private final List<SQLColumnDefinition> columns = new ArrayList<SQLColumnDefinition>();

    // for mysql
    private SQLName firstColumn;
    private SQLName afterColumn;
    private boolean first;
    private Boolean restrict;
    private boolean cascade;
    private boolean ifNotExists;

    public SQLAlterTableAddColumn() {
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, columns);
        }
        visitor.endVisit(this);
    }

    public boolean isIfNotExists() {
        return ifNotExists;
    }

    public void setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
    }

    public List<SQLColumnDefinition> getColumns() {
        return columns;
    }

    public void addColumn(SQLColumnDefinition column) {
        if (column != null) {
            column.setParent(this);
        }
        this.columns.add(column);
    }

    public SQLName getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(SQLName first) {
        this.firstColumn = first;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public SQLName getAfterColumn() {
        return afterColumn;
    }

    public void setAfterColumn(SQLName after) {
        this.afterColumn = after;
    }

    public Boolean getRestrict() {
        return restrict;
    }

    public boolean isRestrict() {
        if (restrict == null) {
            return !cascade;
        }
        return restrict;
    }

    public void setRestrict(boolean restrict) {
        this.restrict = restrict;
    }

    public boolean isCascade() {
        return cascade;
    }

    public void setCascade(boolean cascade) {
        this.cascade = cascade;
    }
}
