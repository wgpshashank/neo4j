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
package org.neo4j.bolt.messaging.v1.message;

import org.neo4j.kernel.api.exceptions.Status;
import org.neo4j.bolt.messaging.v1.MessageHandler;

public class FailureMessage implements Message
{
    private final Status status;
    private final String message;

    public FailureMessage( Status status, String message )
    {
        this.status = status;
        this.message = message;
    }

    public Status status()
    {
        return status;
    }

    public String message()
    {
        return message;
    }

    @Override
    public <E extends Exception> void dispatch( MessageHandler<E> consumer ) throws E
    {
        consumer.handleFailureMessage( status, message );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( !(o instanceof FailureMessage) ) return false;

        FailureMessage that = (FailureMessage) o;

        if ( message != null ? !message.equals( that.message ) : that.message != null )
            return false;
        if ( status != null ? !status.equals( that.status ) : that.status != null ) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "FailureMessage{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

}
