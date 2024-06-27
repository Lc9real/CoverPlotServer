import java.io.Serializable;

public class Message implements Serializable
{
    public int id;
    public Object data;
    public Object[] args;

    public Message()
    {

    }

    public Message(int id, Object function, Object[] args)
    {
        this.id = id;
        this.data = function;
        this.args = args;
    }

    public Message(int id, Object data)
    {
        this.id = id;
        this.data = data;
    }

}
