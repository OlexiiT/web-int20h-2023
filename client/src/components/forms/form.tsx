type Props = {
  children: React.ReactNode;
  captionText?: string;
};

export default function Form({ children, captionText }: Props) {
  return (
    <form>
      {captionText && <FormCaption text={captionText} />}
      {children}
    </form>
  );
}

type FormCaptionProps = {
  text: string;
};

function FormCaption({ text }: FormCaptionProps) {
    return <h1>{text}</h1>  
}
