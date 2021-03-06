/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server.rrd.sampler;

import org.rrd4j.DsType;

import org.neo4j.kernel.impl.store.NeoStores;
import org.neo4j.kernel.impl.transaction.state.NeoStoresSupplier;
import org.neo4j.server.rrd.Sampleable;

public abstract class DatabasePrimitivesSampleableBase implements Sampleable
{
    private final NeoStoresSupplier neoStoresSupplier;

    public DatabasePrimitivesSampleableBase( NeoStoresSupplier neoStoresSupplier )
    {
        if( neoStoresSupplier == null )
        {
            throw new RuntimeException( "Database sampler needs a NeoStores to work, was given null." );
        }
        this.neoStoresSupplier = neoStoresSupplier;

    }

    protected NeoStores getNeoStores()
    {
        return neoStoresSupplier.get();
    }

    @Override
    public DsType getType()
    {
        return DsType.GAUGE;
    }
}
